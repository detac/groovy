package basic

def myClosure = {
    println new Date()
    sleep(100)
}

(1..3).each({ i->
    println "$i"
    myClosure()
})

println ''

println 'findAll'
(1..10).findAll({it!=0 && it%2==0}).each({println "In closure: $it"})