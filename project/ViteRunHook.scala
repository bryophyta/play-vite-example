import play.sbt._
import sbt._
import sbt.Keys._
import play.sbt.PlayImport._

import scala.sys.process.Process

object VitePlugin extends sbt.AutoPlugin {
  override def requires = PlayScala
  override def trigger = noTrigger

  override def projectSettings = Seq(
    PlayKeys.playRunHooks += new ViteBuildHook(baseDirectory.value)
  )
}

class ViteBuildHook(base:File) extends PlayRunHook {
  var process: Option[Process] = None

  override def afterStarted(): Unit = {
    process = Some(Process("npm run dev", base / "client").run)
  }

  override def afterStopped(): Unit = {
    process.foreach(_.destroy())
    process = None
  }
}
