#!/bin/sh

# cool trick found in 'treebolic' ; DON'T USE FUNCTION
followlink()
{
	prg="$1"
	while [ -h "$prg" ] ; do
		ls=`ls -ld "$prg"`
		link=`expr "$ls" : '.*-> \(.*\)$'`
		if expr "$link" : '.*/.*' > /dev/null; then
			prg="$link"
		else
			prg=`dirname "$prg"`/"$link"
		fi
	done
	echo $prg
}

absdir() 
{ 
	[ -n "$1" ] && ( cd "$1" 2> /dev/null && pwd ; ) 
}

where=`followlink $0`
where=`dirname ${where}`
where=`absdir ${where}`

java -cp ${where}/target/scala_2.8.0/scalainterpreterpane_2.8.0-0.16.jar:${where}/lib_managed/scala_2.8.0/compile/jsyntaxpane-0.9.5-b29.jar:${SCALA_HOME}/lib/scala-library.jar:${SCALA_HOME}/lib/scala-compiler.jar de.sciss.scalainterpreter.Main
