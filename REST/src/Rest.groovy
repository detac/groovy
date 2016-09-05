@Grapes([
    @Grab(
        group   = 'org.codehaus.groovy.modules.http-builder',
        module  = 'http-builder',
        version = '0.6'
    )
])

import groovyx.net.http.RESTClient

def forecastApi = new RESTClient('https://api.forecast.io')
def apiKey = ''

