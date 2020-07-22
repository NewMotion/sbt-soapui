sbt-soapui
==========

an sbt plugin to automatically launch mock services defined within a soapui project

## Requirements

* [SBT 1.3+](http://www.scala-sbt.org/)


## Quick start

Add plugin to *project/plugins.sbt*:

```scala

resolvers += "TNM" at "http://nexus.thenewmotion.com/content/repositories/releases-public"

addSbtPlugin("com.newmotion" % "sbt-soapui-mockservice" % "0.2.0")
```

For *.sbt* build definitions, inject the plugin settings in *build.sbt*:

```scala
seq(soapui.settings :_*)
```

For *.scala* build definitions, inject the plugin settings in *Build.scala*:

```scala
Project(..., settings = Project.defaultSettings ++ com.thenewmotion.sbt.plugins.SoapUIMockServicePlugin.soapui.settings)
```

## Configuration

Plugin keys are located in `com.thenewmotion.sbt.plugins.SoapUIMockServicePlugin.Keys`

### Add SoapUI projects

```scala
soapui.mockServices := Seq(
  soapui.MockService( (resourceDirectory in IntegrationTest).value / "soapui-project.xml", "9001"),
  ...
) 
```
