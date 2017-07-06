package git.pull

import groovy.json.JsonSlurper

println ''
println '*** GIT PULL START *** '
println ''

def properties = new JsonSlurper().parse(new File('pullAll.json'))

def results = [:]

def encoding = 'UTF-8'

properties.repositories.each { repository ->
    println ''
    println '*** Repository : ' + repository.name + ' START *** '
    println ''

    def cd = 'cmd /c cd ' + repository.location
	
	println '> git clean'
	def clean = cd + ' && git clean -fd'
	clean.execute().waitForProcessOutput(System.out, System.err)
	println ''		

    repository.remotes.each { remote ->
        println ''
        println '*** Remote : ' + remote + ' START *** '
        println ''

        println '> git fetch ' + remote
        def fetch = cd + ' && git fetch ' + remote
        fetch.execute().waitForProcessOutput(System.out, System.err)
        
        println ''
        println '*** Remote : ' + remote + ' END *** '
    }

    repository.branches.each { branch ->

        println ''
        println '*** Branch : ' + branch + ' START *** '
        println ''
		
        println '> git checkout ' + branch
        def checkout_branch = cd + ' && git checkout ' + branch
        checkout_branch.execute().waitForProcessOutput(System.out, System.err)
        println ''	
		
        println '> git pull'
        def pull = cd + ' && git pull'
        pull.execute().waitForProcessOutput(System.out, System.err)

        def status = cd + ' && git status'
        def result = ''

        def output = new ByteArrayOutputStream()
        status.execute().waitForProcessOutput(output, output)

        def key = repository.name + ' | ' + branch
        results[key] = new String(output.toByteArray(), encoding);

        println ''
        println '*** Branch : ' + branch + ' END *** '
    }

    if (repository.branches.size > 1) {
        println ''
        println '*** Checkout main branch START ***'
        println '> git checkout ' + repository.main_branch
        def checkout_main_branch = cd + ' && git checkout ' + repository.main_branch
        checkout_main_branch.execute().waitForProcessOutput(System.out, System.err)
        println '*** Checkout main branch END ***'
    }

    println ''
    println '*** Repository : ' + repository.name + ' END *** '
}

println ''
println '*** GIT PULL END ***'
println ''

println '*** RESULTS ***'
for (it in results) {
    println it.key
    println it.value
}
