package basic

def enum DAYS {MON, TUE, WED, THU, FRI, SAT, SUN}

def weekends = DAYS.MON..DAYS.FRI
for(var in weekends) {
    println var
}

println ''

println 'Extends: '
println weekends.from
println weekends.to
