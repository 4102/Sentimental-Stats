name := "Sentimental-Stats"

version := "0.7"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp"))
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4"
libraryDependencies += "com.github.wookietreiber" %% "scala-chart" % "latest.integration"
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.5.6"
libraryDependencies += "org.jfree" % "jfreesvg" % "3.0"
libraryDependencies += "org.mongodb" %% "casbah" % "2.8.2"


// Allows you to cancel execution early with Ctrl + C
cancelable in Global := true

// Opens project in a forked JVM, allowing sys.exit without throwing exceptions.
fork in run := true

