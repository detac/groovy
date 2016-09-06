package git.pull

import groovy.json.JsonSlurper

println ''
println '*** GIT PULL START *** '
println ''

def properties = new JsonSlurper().parse(new File('properties.json'))

properties.repositories.each { repository ->
    println ''
    println '*** Repository : ' + repository.name + ' START *** '
    println ''

    def cd = 'cmd /c cd ' + repository.location

    println '> git fetch'
    def fetch = cd + ' && git fetch'
    fetch.execute().waitForProcessOutput(System.out, System.err)
    println ''

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