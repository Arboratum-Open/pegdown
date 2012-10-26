repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'http://scala-tools.org/repo-releases'
repositories.remote << 'http://maven.arboratum.com/libs-releases'
repositories.remote << 'http://oss.sonatype.org/content/repositories/releases'
#repositories.remote << 'http://scala-tools.org/repo-snapshots'

VERSION_NUMBER = '1.1.0-ARBO-SNAPSHOT'

desc 'The pegdown project'
define 'pegdown' do
  project.version = VERSION_NUMBER
  project.group = 'org.pegdown'

  manifest['Built-By'] = 'Geoffrey'
  manifest['Specification-Title'] = 'pegdown'
  manifest['Specification-Version'] = VERSION_NUMBER
  manifest['Specification-Vendor'] = 'pegdown.org'
  manifest['Implementation-Title'] = 'pegdown'
  manifest['Implementation-Version'] = "#{VERSION_NUMBER}"
  manifest['Implementation-Vendor'] = 'pegdown.org'
  manifest['Bundle-License'] = 'http://www.apache.org/licenses/LICENSE-2.0.txt'
  manifest['Bundle-Version'] = VERSION_NUMBER
  manifest['Bundle-Description'] = 'pegdown, a Java 1.5+ library providing a clean and lightweight markdown processor'
  manifest['Bundle-Name'] = 'pegdown'
  manifest['Bundle-DocURL'] = 'http://www.pegdown.org'
  manifest['Bundle-Vendor'] = 'pegdown.org'
  manifest['Bundle-SymbolicName'] = 'org.pegdown'
  
  meta_inf << file('NOTICE')
                                    
  PARBOILED_VERSION = '1.0.2'
  PARBOILED = [
          "org.parboiled:parboiled-core:jar:#{PARBOILED_VERSION}",
          "org.parboiled:parboiled-core:jar:sources:#{PARBOILED_VERSION}",
          transitive("org.parboiled:parboiled-java:jar:#{PARBOILED_VERSION}"),
          "org.parboiled:parboiled-java:jar:sources:#{PARBOILED_VERSION}"
  ]
  
  PLANTUML_VERSION = '7938'
  PLANTUML = [
  		transitive("net.sourceforge.plantuml:plantuml:jar:#{PLANTUML_VERSION}"),
  		"net.sourceforge.plantuml:plantuml:jar:sources:#{PLANTUML_VERSION}"
  ]
  
  JTIDY = "net.sf.jtidy:jtidy:jar:r938"

  compile.with PARBOILED + PLANTUML
  compile.using :deprecation => true, :target => '1.5', :other => ['-encoding', 'UTF-8'], :lint=> 'all'

  test.with JTIDY
  test.using :testng

  doc.using :windowtitle=>"pegdown #{VERSION_NUMBER} API"
  
  package(:jar).pom.from file('pom.xml')
  package :sources
  package :javadoc  
end
