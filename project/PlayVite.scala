import sbt._
import sbt.Keys._
import play.sbt._
import play.sbt.PlayImport._
import com.typesafe.sbt.web.Import.Assets
import com.typesafe.sbt.web.Import.WebKeys._

import Path.rebase

import scala.sys.process.Process
import com.typesafe.sbt.web.SbtWeb
import play.sbt.run.PlayRun

object PlayVite extends sbt.AutoPlugin {
  override def requires = PlayScala && SbtWeb
  override def trigger = noTrigger

  lazy val viteBuildAssets =
    taskKey[Seq[File]]("Build assets using `vite build`")

  override def projectSettings = Seq(
    PlayKeys.playRunHooks += new ViteBuildHook(baseDirectory.value),
    Assets / classDirectory := webTarget.value,
    Assets / unmanagedResourceDirectories += (baseDirectory.value / "client" / "dist"),
    viteBuildAssets := {
      val build = Process(
        "npm run build -- --base /assets",
        baseDirectory.value / "client"
      ).!

      if (build != 0)
        throw new RuntimeException(
          "Assets compilation failed - see error messages above"
        )

      val buildOutputDir = baseDirectory.value / "client" / "dist"
      buildOutputDir.**("*").get
    },
    Assets / packageBin := (Assets / packageBin)
      .dependsOn(viteBuildAssets)
      .value
  )
}

class ViteBuildHook(base: File) extends PlayRunHook {
  var process: Option[Process] = None

  override def afterStarted(): Unit = {
    process = Some(
      Process("npm run dev -- --base /assets", base / "client").run
    )
  }

  override def afterStopped(): Unit = {
    process.foreach(_.destroy())
    process = None
  }
}
