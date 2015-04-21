import sbtrelease.ReleasePlugin.ReleaseKeys.crossBuild

lazy val soapuiVersion = "4.5.0"

val common = Seq(
    resolvers += "SOAPUI Repository" at "http://www.soapui.org/repository/maven2",
    organization := "com.thenewmotion",
    crossBuild := false,
    scalaVersion := tnm.ScalaVersion.prev
)

val ext = project.in(file("soapui-ext"))
    .enablePlugins(OssLibPlugin)
    .settings(common: _*)
    .settings(
        name := "soapui-ext",
        version := soapuiVersion,
        libraryDependencies ++= Seq(
            "eviware" % "soapui" % soapuiVersion % "provided",
            "jetty"   % "jetty"  % "6.1.26"      % "provided",
            "log4j"   % "log4j"  % "1.2.14"      % "provided"
        )
    )

val mockService = project.in(file("sbt-soapui-mockservice"))
    .enablePlugins(OssLibPlugin)
    .dependsOn(ext)
    .settings(common: _*)
    .settings(
        name := "sbt-soapui-mockservice",
        sbtPlugin := true
    )

publish := {}