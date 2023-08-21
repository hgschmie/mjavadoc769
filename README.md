# maven-javadoc-plugin can not deal with optional dependencies

Checkout this repository, then build with `mvn clean install`

=> This creates two artifacts and a javadoc artifact for the demo project.

Now activate automatic module names:

`mvn -Pauto-module-name clean install`.

=> This still works, but the artifacts are now Java 9 JPMS modules with automatic module names.

Now build for Java 11 but do not build JPMS modules:

`mvn -Djavadoc.version=11 -Pauto-module-name clean install`

=> Still works, creates artifacts and javadoc.

Now activate both:

`mvn -Pauto-module-name -Djavadoc.version=11 -Pauto-module-name clean install`

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-javadoc-plugin:3.5.0:jar (basepom.default) on project mjavadoc769: MavenReportException: Error while generating Javadoc:
[ERROR] Exit code: 1
[ERROR] /Users/henning/scratch/mjavadoc769/src/main/java/mavenbugs/mjavadoc769/InternalImportBindingBuilder.java:88: error: cannot access Provider
[ERROR]     static final class InternalBindingProvider<T> implements Provider<T> {
[ERROR]                  ^
[ERROR]   class file for javax.inject.Provider not found
[ERROR] 1 error
[ERROR] Command line was: /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/bin/javadoc -J-Xmx1024m @options @packages
[ERROR]
[ERROR] Refer to the generated Javadoc files in '/Users/henning/scratch/mjavadoc769/target/apidocs' dir.
[ERROR]
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
```

This crashes the javadoc plugin. Note that all other plugins (compiler, surefire etc.) work fine in that configuration.
