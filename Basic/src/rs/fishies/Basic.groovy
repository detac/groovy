package rs.fishies

def legends = ['Toma', 'Miroslav', 'Rade']
def greeting = 'Ola, '

for(int i=0;i<legends.size();i++) {
    println "$greeting" + "${legends[i]}" + ' > ' + '${i*10}' + ':' + "${i*10}"
}
println ''

for(legend in legends) {
    println "${legend}"
}