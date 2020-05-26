/*build.sbt*/

import sbtcrossproject.CrossPlugin.autoImport.crossProject

inThisBuild(Def.settings(
  scalaVersion := "2.11.12"
))

val app = crossProject(JSPlatform, JVMPlatform).settings(
  unmanagedSourceDirectories in Compile +=
    baseDirectory.value  / "shared" / "main" / "scala",
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "scalatags" % "0.6.2",
    "com.lihaoyi" %%% "upickle" % "0.4.4"
  )
).jsSettings(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1"
  )
).jvmSettings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
    "com.typesafe.akka" %% "akka-actor" % "2.4.12",
    "org.webjars" % "bootstrap" % "3.2.0"
  )
)

lazy val appJS = app.js
lazy val appJVM = app.jvm.settings(
  (resources in Compile) += (fastOptJS in (appJS, Compile)).value.data
)
