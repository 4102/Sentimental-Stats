name := "Sentimental-Stats"

version := "0.3"

scalaVersion := "2.11.8"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "1.1.0"

// Allows you to cancel execution early with Ctrl + C
cancelable in Global := true

// Opens project in a forked JVM, allowing sys.exit without throwing exceptions.
fork in run := true