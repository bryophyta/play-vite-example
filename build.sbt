name := """play-vite-example"""
organization := "uk.me.nowak"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, VitePlugin)

scalaVersion := "2.13.10"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += ws

// Adds additional packages into Twirl
TwirlKeys.templateImports += "play.api._" //"uk.me.nowak.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "uk.me.nowak.binders._"
