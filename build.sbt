resolvers += (")SOAPUI Repository" at "https://www.soapui.org/repository/maven2")

ThisBuild / organization := "com.newmotion"
ThisBuild / scalaVersion := tnm.ScalaVersion.prev

def proj(name: String) =
  Project(name, file(name))
    .enablePlugins(OssLibPlugin)

val ext = proj("soapui-ext")
  .settings(
    scalaVersion := tnm.ScalaVersion.prev,
    crossScalaVersions := Seq(tnm.ScalaVersion.curr, tnm.ScalaVersion.prev),
    libraryDependencies ++= Seq(
      "eviware" % "soapui" % "4.5.0" % "provided",
      "jetty" % "jetty" % "6.1.26" % "provided",
      "log4j" % "log4j" % "1.2.17" % "provided"
    ),
    publishArtifact in (Compile, packageDoc) := false
  )

val mockService = proj("sbt-soapui-mockservice")
  .dependsOn(ext)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    sbtPlugin := true,
    scalaVersion := tnm.ScalaVersion.prev,
    crossScalaVersions := Seq(tnm.ScalaVersion.prev),
  )

lazy val root = project
  .in(file("."))
  .settings(skip in publish := true)
  .aggregate(mockService, ext)
