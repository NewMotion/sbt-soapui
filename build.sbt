import sbt.Keys.crossScalaVersions

def proj(name: String) =
  Project(name, file(name))
    .enablePlugins(OssLibPlugin)
    .settings(
      resolvers += "SOAPUI Repository" at "https://www.soapui.org/repository/maven2",
      organization := "com.newmotion",
      scalaVersion := tnm.ScalaVersion.prev,
      crossScalaVersions := Seq(tnm.ScalaVersion.prev)
    )

val ext = proj("soapui-ext")
  .settings(
    libraryDependencies ++= Seq(
      "eviware" % "soapui" % "4.5.0" % "provided",
      "jetty" % "jetty" % "6.1.26" % "provided",
      "log4j" % "log4j" % "1.2.17" % "provided"
    ),
    publishArtifact in (Compile, packageDoc) := false
  )

val mockService = proj("sbt-soapui-mockservice")
  .dependsOn(ext)
  .settings(
    sbtPlugin := true
  )

enablePlugins(OssLibPlugin)
publish := {}
scalaVersion := tnm.ScalaVersion.prev
crossScalaVersions := Seq(tnm.ScalaVersion.prev)
publishArtifact in (Compile, packageDoc) := false