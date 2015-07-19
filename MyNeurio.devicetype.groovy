/**
 *  My Neurio Device
 *
 *  Copyright 2015 Yves Racine
 *  linkedIn profile: ca.linkedin.com/pub/yves-racine-m-sc-a/0/406/4b/
 *  Refer to readme file for installation instructions.
 *
 *  Code: https://github.com/yracine/device-type.myneurio
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

import java.text.SimpleDateFormat

// for the UI
preferences {
	input("appKey", "text", title: "App Key (public)", description:
		"The application (public) key given by Neurio (no spaces)")
	input("privateKey", "text", title: "Private Key", description:
		"The private key given by Neurio (no spaces)")
	input("sensorId", "number", title: "sensorId (optional) [default=first Neurio found,MAC Address in hexa,format=0x0000XXXXXXXXXXXX]", description:
		"The MAC address of your Neurio Sensor (no spaces and no ':')")
	input("trace", "text", title: "trace", description:
		"Set it to true to enable tracing (no spaces) or leave it empty (no tracing)"
	)
    
}

metadata {
	definition (name: "My Neurio Device V2", namespace: "yracine", author: "Yves Racine") {
		capability "Power Meter"
		capability "Refresh"
		capability "Polling"
		capability "Energy Meter"

		command "setAuthTokens"
		command "getCurrentUserInfo"
		command "getSampleStats"
		command "getLastLiveSamples"
		command "generateSampleStats"
		command "getApplianceData"
		command "getApplianceList"
		command "generateAppliancesStats"
		command "generateAppliancesEvents"
        
		attribute "verboseTrace","string"
		attribute "generationEnergy","string"
		attribute "generationPower","string"
		attribute "userid","string"
		attribute "username", "string"
		attribute "email","string"
		attribute "locationId","string"
		attribute "locationName","string"
		attribute "timezone","string"
		attribute "sensorId","string"

		// Consumed Energy attributes

		attribute "consTotalInPeriod","string"
		attribute "consEnergyDay","string"
		attribute "consEnergyWeek","string"
		attribute "consEnergyMonth","string"
		attribute "consEnergy2DaysAgo","string"
		attribute "consEnergy2WeeksAgo","string"
		attribute "consEnergy2MonthsAgo","string"

		// Generated Energy attributes
        
		attribute "genTotalInPeriod","string"
		attribute "genEnergyDay","string"
		attribute "genEnergyWeek","string"
		attribute "genEnergyMonth","string"
		attribute "genEnergy2DaysAgo","string"
		attribute "genEnergy2WeeksAgo","string"
		attribute "genEnergy2MonthsAgo","string"

		attribute "applianceData","string"
		attribute "appliancesData","string"
		attribute "appliancesList","string"

		attribute "appliancesEventsData","string"
		attribute "appliancesStatsData","string"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	// UI tile definitions
	tiles {
		valueTile(	"power","device.power",unit: 'Watts', width: 1, height: 1,canChangeIcon: false)
        	{
            		state(	"power", label:'Power\n${currentValue}W',
					backgroundColor: "#ffffff"
/*
					backgroundColors: [
						[value: 500, color: "green"],
						[value: 3000, color: "orange"],
						[value: 6000, color: "red"]
                  			]
*/                            
				)
		}
		valueTile(	"energy", "device.energy", unit:'kWh',width: 1, height: 1,canChangeIcon: false
				) 
        	{
			state("energy",
					label:'Energy\n ${currentValue}kWh',
					backgroundColor: "#ffffff",
                 		)
		}
		valueTile(	"genPower", "device.generationPower",unit: 'Watts',width: 1, height: 1,canChangeIcon: false,
 				)
		{
			state(	"genPower", label:'GenPower\n${currentValue}',
					backgroundColor: "#ffffff"
				)
		}
 		valueTile(	"genEnergy",  "device.generationEnergy", unit: 'kWh',width: 1, height: 1,canChangeIcon: false,
				) 
        	{
        		state("genEnergy",
					label:'GenEnergy\n${currentValue}',
					backgroundColor: "#ffffff"
				)
		}
        
		 
		valueTile(	"consEnergyYesterday", "device.consEnergyDay", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
 					decoration: "flat"
				) 
        	{
			state("default",
					label:'Yesterday\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"consEnergyLastWeek", "device.consEnergyWeek", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat"
				)
        	{
			state("default",
					label:'Week\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"consEnergyLastMonth", "device.consEnergyMonth", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat"
				)
        	{
			state("default",
					label:'Month\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"consEnergy2DaysAgo", "device.consEnergy2DaysAgo", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
 					decoration: "flat"
				) 
        	{
			state("default",
					label:'2DaysAgo\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"consEnergy2WeeksAgo", "device.consEnergy2WeeksAgo", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat",
				)
        	{
			state("default",
					label:'2WksAgo\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"consEnergy2MonthsAgo", "device.consEnergy2MonthsAgo", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat"
				)
        	{
			state("default",
					label:'2MonthAgo\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
        
		valueTile(	"genEnergyYesterday", "device.genEnergyDay", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
 					decoration: "flat"
				) 
        	{
			state("default",
					label:'Yesterday\nGen\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"genEnergyLastWeek", "device.genEnergyWeek", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat"
				)
        	{
			state("default",
					label:'Week\nGen\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"genEnergyLastMonth", "device.genEnergyMonth", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat"
				)
        	{
			state("default",
					label:'Month\nGen\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"genEnergy2DaysAgo", "device.genEnergy2DaysAgo", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
 					decoration: "flat"
				) 
        	{
			state("default",
					label:'2DaysAgo\nGen\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"genEnergy2WeeksAgo", "device.genEnergy2WeeksAgo", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat",
				)
        	{
			state("default",
					label:'2WksAgo\nGen\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
		valueTile(	"genEnergy2MonthsAgo", "device.genEnergy2MonthsAgo", unit:'kWh',width: 1, height: 1,canChangeIcon: false,
  					decoration: "flat"
				)
        	{
			state("default",
					label:'2MonthAgo\nGen\n${currentValue}kWh',
					backgroundColor: "#ffffff"
				)
		}
       
		standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat") {
			state "default", action:"polling.poll", icon:"st.secondary.refresh"
		}

		main(["power", "energy"])
		details(["power", "energy",  "refresh", "genPower", "genEnergy", "consEnergyYesterday", "consEnergy2DaysAgo", "consEnergyLastWeek",  "consEnergy2WeeksAgo",
        		"consEnergyLastMonth","consEnergy2MonthsAgo", "genEnergyYesterday", "genEnergy2DaysAgo", "genEnergyLastWeek",  "genEnergy2WeeksAgo",
        		"genEnergyLastMonth","genEnergy2MonthsAgo"])


	}
}

// handle commands

 

void poll() {
    
//	getCurrentUserInfo() must be called prior to poll()
	
	def sensorId= determine_sensor_id("") 	    
    
	getLastLiveSamples(sensorId)
    
	String exceptionCheck = device.currentValue("verboseTrace").toString()
	if ((exceptionCheck.contains("exception")) || (exceptionCheck.contains("error"))) {  
	// check if there is any exception or an error reported in the verboseTrace associated to the device 
		log.error "poll>sensorId=$sensorId,$exceptionCheck" 
		return    
	}

	def dataEvents = [
		userid:data?.user.id,
		username:(data?.user.name) && (data?.user.name != 'null')?data.user.name:"",
		email:data?.user.email,
		locationName:data?.user.locations[0].name,
		timezone:data?.user.locations[0].timezone,
		sensorId:data?.user.locations[0]?.sensors[0]?.id,
		power:data?.liveSamples.consumptionPower,
		energy:data?.liveSamples.consumptionEnergy,
		generationPower:data?.liveSamples.generationPower,
		generationEnergy:data?.liveSamples.generationEnergy
	]
	generateEvent(dataEvents)
	String dateInLocalTime = new Date().format("yyyy-MM-dd", location.timeZone)
        
	// generate the stats only once every day
    
	if (state.lastGeneratedDate != dateInLocalTime) {
		if (settings.trace) {
			log.debug "poll>about to generateSampleStats,dateInLocalTime=${dateInLocalTime},state.lastGeneratedDate= $state.lastGeneratedDate"
		}
		    
		generateSampleStats("")
		state.lastGeneratedDate= dateInLocalTime       
	}
	sendEvent name: "verboseTrace", value:
			"poll>done for sensorId = ${sensorId}"

}


private void generateEvent(Map results) {
	if (settings.trace) {
		log.debug "generateEvent>parsing data $results"
	}
    
	if(results) {
		results.each { name, value ->
			def isDisplayed = true

// 			Energy variable names contain "energy"           

			if ((name.toUpperCase().contains("ENERGY"))) {  
				Double energyValue = getEnergy(value.toDouble()).round()
				String energyValueString = String.format("%5d", energyValue.intValue())                
				def isChange = isStateChange(device, name, energyValueString)
				isDisplayed = isChange
                
				sendEvent(name: name, value: energyValueString, unit: "kWh")                                     									 
			} else if (name.toUpperCase().contains("POWER")) { // power variable names contain 'power'

// 			Power variable names contain "power"

 				Long powerValue = value.toLong()
				def isChange = isStateChange(device, name, powerValue.toString())
				isDisplayed = isChange
				sendEvent(name: name, value: powerValue.toString(), unit: "Watts")                                     									 

 			} else {
				def isChange = isStateChange(device, name, value)
				isDisplayed = isChange

				sendEvent(name: name, value: value, isStateChange: isChange)       
			}
		}
	}
}




private def getEnergy(value) {
	if (value ==null) {
		return 0    
	}

	// conversion from watts-sec to KWh 
	return (value/ (60*60*1000))
}

void refresh() {
	poll()
}
private def api(method, args, success={}) {

	String URI_ROOT = "${get_URI_ROOT()}"
	if (!isLoggedIn()) {
		login()
	}
	if (isTokenExpired()) {
		if (settings.trace) {
			log.debug "api> need to refresh tokens"
		}
/*
		refresh_tokens()

*/
		login()
	}
	def methods = [
		'currentUserInfo': 
			[uri:"${URI_ROOT}/users/current", type: 'get'],
		'lastLiveSamples': 
			[uri:"${URI_ROOT}/samples/live/last?${args}", 
          		type: 'get'],
		'sampleStats': 
			[uri:"${URI_ROOT}/samples/stats?${args}", 
          		type: 'get'],
		'appliancesList': 
			[uri:"${URI_ROOT}/appliances?${args}", 
          		type: 'get'],
		'applianceData': 
			[uri:"${URI_ROOT}/appliances/${args}", 
          		type: 'get'],
		'appliancesStats': 
			[uri:"${URI_ROOT}/appliances/stats?${args}", 
          		type: 'get'],
		'appliancesEvents': 
			[uri:"${URI_ROOT}/appliances/events?${args}", 
          		type: 'get']
		]
	def request = methods.getAt(method)
	if (settings.trace) {
		sendEvent name: "verboseTrace", value:
			"api> about to call doRequest with (unencoded) args = ${args}"
	}
	doRequest(request.uri, args, request.type, success)
}

// Need to be authenticated in before this is called. So don't call this. Call api.
private def doRequest(uri, args, type, success) {
        
	def params = [
		uri: uri,
		headers: [
			'Authorization': "${data.auth.token_type} ${data.auth.access_token}",
			'Content-Type': "application/json",
			'charset': "UTF-8",
			'Accept': "application/json"
		],
		body: args
	]
	try {
		if (settings.trace) {
//			sendEvent name: "verboseTrace", value: "doRequest>token= ${data.auth.access_token}"
			sendEvent name: "verboseTrace", value:
				"doRequest>about to ${type} with uri ${params.uri}, args= ${args}"
				log.debug "doRequest> ${type}> uri ${params.uri}, args= ${args}"
		}
		if (type == 'post') {
			httpPostJson(params, success) 
		} else if (type == 'get') {
			params.body = null // parameters already in the URL request
			httpGet(params, success) 
		}
	} catch (java.net.UnknownHostException e) {
		log.error "doRequest> Unknown host - check the URL " + params.uri
		sendEvent name: "verboseTrace", value: "doRequest>exception $e, Unknown host ${params.uri}" 
		throw e        
	} catch (java.net.NoRouteToHostException e) {
		log.error "doRequest> No route to host - check the URL " + params.uri
		sendEvent name: "verboseTrace", value: "doRequest>exception $e, No route to host ${params.uri}"
		throw e        
	} catch (e) {
		log.error "doRequest> exception $e " + params.uri
		sendEvent name: "verboseTrace", value: "doRequest>exception $e at ${params.uri}" 
		throw e        
	}
}


void getLastLiveSamples(sensorId) {
	def NEURIO_SUCCESS=200

	sensorId= determine_sensor_id(sensorId) 	    
	def statusCode=true
	int j=0        
	def bodyReq="sensorId=" + sensorId 
    
	while ((statusCode!= NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		api('lastLiveSamples', bodyReq) {resp->
		
			statusCode= resp.status
			if (statusCode == NEURIO_SUCCESS) {
				data?.liveSamples=resp.data
				def consumptionPower = data.liveSamples?.consumptionPower
				def consumptionEnergy = data.liveSamples?.consumptionEnergy
				def generationPower = data.liveSamples?.generationPower
				def generationEnergy = data.liveSamples?.generationEnergy
				def timestamp = data.liveSamples?.timestamp    
				if (settings.trace) {
					sendEvent name: "verboseTrace", value:"getLastLiveSamples>sensorId=${sensorId},consumptionPower=${consumptionPower},consumptionEnergy=${consumptionEnergy}" +
						",generationPower=${generationPower},generationEnergy=${generationEnergy}"
				}
				sendEvent name: "verboseTrace", value:"getLastLiveSamples>done for sensorId=${sensorId}"
			} else {
				def message = resp.message
            			def errors = resp.errors
				log.error "getLastLiveSamples>status=${statusCode.toString()},message=${message},errors=${errors} for sensorId=${sensorId}"
				sendEvent name: "verboseTrace", value:"getLastLiveSamples>status=${statusCode.toString()},message=${message},error=${errors} for sensorId=${sensorId}"
			}                
		}  /* end api call */              
	} /* end while */
}

void generateSampleStats(sensorId) {

// generate stats for yesterday

	String dateInLocalTime = new Date().format("yyyy-MM-dd", location.timeZone) 
	String timezone = new Date().format("zzz", location.timeZone)
	String dateAtMidnight = dateInLocalTime + " 00:00 " + timezone    
    
	if (settings.trace) {
		log.debug("generateSamplesStats>date in local date/time= ${dateAtMidnight}")
	}
	Date endDate = formatDate(dateInLocalTime)
	Date startDate = (endDate -1)

	String nowInLocalTime = new Date().format("yyyy-MM-dd HH:mm", location.timeZone)
	if (settings.trace) {
		log.debug("generateSampleStats>yesterday: local date/time= ${nowInLocalTime}, startDate in UTC = ${String.format('%tF %<tT',startDate)}," +
			"endDate in UTC= ${String.format('%tF %<tT', endDate)}")
	}
	getSampleStats(sensorId,startDate,endDate,"days",null)
	Long totalConsInPeriod =  device.currentValue("consTotalInPeriod").toLong()
	Long totalGenInPeriod =  device.currentValue("genTotalInPeriod").toLong()
	def dataStats = ['consEnergyDay':totalConsInPeriod, 'genEnergyDay':totalGenInPeriod]    

// generate stats for 2 days ago

	endDate=startDate
	startDate = (endDate -1)

	if (settings.trace) {
		log.debug("generateSampleStats>2 days ago: startDate in UTC = ${String.format('%tF %<tT',startDate)}," +
			"endDate in UTC= ${String.format('%tF %<tT', endDate)}")
	}
	getSampleStats(sensorId,startDate,endDate,"days",null)
	totalConsInPeriod =  device.currentValue("consTotalInPeriod").toLong()
	totalGenInPeriod =  device.currentValue("genTotalInPeriod").toLong()
	dataStats = dataStats + ['consEnergy2DaysAgo':totalConsInPeriod, 'genEnergy2DaysAgo':totalGenInPeriod]    

// generate stats for the past week

	startDate = (endDate -7)

	if (settings.trace) {
		log.debug("generateSampleStats>past week (last 7 days): startDate in UTC = ${String.format('%tF %<tT',startDate)}," +
			"endDate in UTC= ${String.format('%tF %<tT', endDate)}")
	}
	getSampleStats(sensorId, startDate,endDate,"weeks",null)
	totalConsInPeriod =  device.currentValue("consTotalInPeriod").toLong()
	totalGenInPeriod =  device.currentValue("genTotalInPeriod").toLong()
	dataStats = dataStats + ['consEnergyWeek':totalConsInPeriod, 'genEnergyWeek':totalGenInPeriod]    


// generate stats for 2 weeks ago

	endDate = startDate
	startDate = (endDate -7)

	if (settings.trace) {
		log.debug("generateSampleStats>2 weeks ago: startDate in UTC = ${String.format('%tF %<tT',startDate)}," +
			"date/time endDate in UTC= ${String.format('%tF %<tT', endDate)}")
	}
	getSampleStats(sensorId, startDate,endDate,"weeks",null)
	totalConsInPeriod =  device.currentValue("consTotalInPeriod").toLong()
	totalGenInPeriod =  device.currentValue("genTotalInPeriod").toLong()
	dataStats = dataStats + ['consEnergy2WeeksAgo':totalConsInPeriod, 'genEnergy2WeeksAgo':totalGenInPeriod]    

// generate stats for the past month

	endDate = formatDate(dateAtMidnight)
	Calendar oneMonthAgoCal = new GregorianCalendar()
	oneMonthAgoCal.add(Calendar.MONTH, -1)
	Date oneMonthAgo = oneMonthAgoCal.getTime()
	
	if (settings.trace) {
		log.debug("generateSampleStats>past month: startDate in UTC = ${String.format('%tF %<tT',oneMonthAgo)}," +
			"endDate in UTC= ${String.format('%tF %<tT', endDate)}")
	}
	getSampleStats(sensorId, oneMonthAgo,endDate,"months",null)
	totalConsInPeriod =  device.currentValue("consTotalInPeriod").toLong()
	totalGenInPeriod =  device.currentValue("genTotalInPeriod").toLong()
	dataStats = dataStats + ['consEnergyMonth':totalConsInPeriod, 'genEnergyMonth':totalGenInPeriod]    

// generate stats for 2 months ago

	endDate=(oneMonthAgo -1)
	Calendar twoMonthsAgoCal = new GregorianCalendar()
	twoMonthsAgoCal.add(Calendar.MONTH, -2)
	Date twoMonthsAgo = twoMonthsAgoCal.getTime()
	
	if (settings.trace) {
		log.debug("generateSampleStats>2 months ago: startDate in UTC = ${String.format('%tF %<tT',twoMonthsAgo)}," +
			"endDate in UTC= ${String.format('%tF %<tT', endDate)}")
	}
	getSampleStats(sensorId, twoMonthsAgo,endDate,"months",null)
	totalConsInPeriod =  device.currentValue("consTotalInPeriod").toLong()
	totalGenInPeriod =  device.currentValue("genTotalInPeriod").toLong()
	dataStats = dataStats + ['consEnergy2MonthsAgo':totalConsInPeriod, 'genEnergy2MonthsAgo':totalGenInPeriod]    

	generateEvent(dataStats)


}

private def formatDate(dateString) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm zzz")
	Date theDate = sdf.parse(dateString)
	return theDate
}

private def ISOdateFormat(date) {
 	SimpleDateFormat ISO8601format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
	def ISOdate = ISO8601format.format(date)
    
	return ISOdate
}

void getSampleStats(sensorId,start,end,granularity,frequency) {
	def NEURIO_MIN_INTERVAL=5
	def NEURIO_SUCCESS=200
	Long totalConsumedEnergy=0
	Long totalGeneratedEnergy=0
    
	sensorId= determine_sensor_id(sensorId) 	    
	granularity=granularity?.trim()
	frequency = frequency?.trim()

	// make sure start and granularity are defined
	if ((!start) || (!granularity)) {
		log.error "getSamplesData>start and granularity are required parameters"
		sendEvent name: "verboseTrace", value:"getSamplesData>start and granularity are required parameters"
		return 
	}
	
	// make sure that frequency is a multiple of 5 if granularity is in minute
	if ((granularity == 'minutes') && (frequency)) {
		if ((frequency.mod(NEURIO_MIN_INTERVAL)) != 0) {
			log.error "getSamplesData>only multiples of 5 are supported for frequency (${frequency}) when granularity (${granularity}) is in minutes"
			sendEvent name: "verboseTrace", value:  "getSamplesData>only multiples of 5 are supported for frequency (${frequency}) when granularity (${granularity}) is in minutes"
			return 
		}
	}
	// make sure granularity is one of the correct values
	if ((granularity != 'minutes') && (granularity != 'hours') && (granularity != 'days') && (granularity != 'weeks') && (granularity != 'months')) {
		log.error "getSamplesData>only values of [minutes, hours, days, weeks or months] are supported for granularity (${granularity})"
		sendEvent name: "verboseTrace", value: "only values of minutes, hours, days or months are supported for granularity (${granularity})"
		return 
	}
     
	def bodyReq="sensorId=" + sensorId + "&start=" + ISOdateFormat(start) 
	if ((end != null) && (end != "")) {
		bodyReq = bodyReq+ "&end=" + ISOdateFormat(end)    	
	}
	bodyReq = bodyReq+ "&granularity=" + granularity    	
	if ((frequency != null) && (frequency != "")) {
		bodyReq = bodyReq+ "&frequency=" + frequency    	
	}
	int j=0        
	def statusCode=true
    
	while ((statusCode!= NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		api('sampleStats', bodyReq) {resp->
			statusCode = resp.status        
        		if (statusCode == NEURIO_SUCCESS) {
 				data?.sampleStats=resp.data
				data?.sampleStatsCount=data.sampleStats.size()
				if (settings.trace) {
					log.debug "getSampleStats>sensorId=${sensorId},resp data = ${resp.data}" 
                
				}
				if (data.sampleStatsCount>0) {                
					for (i in 0..data.sampleStatsCount-1) {       
						def consumptionEnergy = data.sampleStats[i]?.consumptionEnergy
						if (consumptionEnergy) {
							totalConsumedEnergy =totalConsumedEnergy + consumptionEnergy.toLong()
						}                        
						def generationEnergy = data.sampleStats[i]?.generationEnergy
						if (generationEnergy) {
							totalGeneratedEnergy =totalGeneratedEnergy + generationEnergy.toLong()
						}                        

						def startTime = data.sampleStats[i].start
						def endTime = data.sampleStats[i].end
						if (settings.trace) {
							sendEvent name: "verboseTrace", value: "getSampleStats>sensorId=${sensorId},sampleStat no ${i},consumptionEnergy=${consumptionEnergy},generationEnergy=${generationEnergy},start=${startTime},end=${endTime}"
						}
                        
					} /* end for samples */
				} /* end if */                    
				sendEvent name: "verboseTrace", value:"getSamplesData>done for sensorId=${sensorId}"
			} else {
				def message = resp.message
				def errors = resp.errors
				log.error "getSamplesData>status=${statusCode.toString()},message=${message},error=${errors} for sensorId=${sensorId}"
				sendEvent name: "verboseTrace", value:"getSamplesData>status=${statusCode.toString()},message=${message},error=${errors} for sensorId=${sensorId}"
			} /* end if statusCode */               
		}  /* end api call */              
	} /* end while */
	generateEvent([consTotalInPeriod: totalConsumedEnergy.toString(), genTotalInPeriod:totalGeneratedEnergy.toString()])

}


//	applianceId - Id of the appliance 

void getApplianceData(applianceId) {
	def NEURIO_SUCCESS=200
    
	def statusCode=true
	int j=0        
	def bodyReq=applianceId 
    
	while ((statusCode!= NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		api('applianceData', bodyReq) {resp->
		
			statusCode= resp.status
			if (statusCode == NEURIO_SUCCESS) {
				data?.appliance=resp.data
				if (settings.trace) {
					def applianceName=data.appliance?.name
					def applianceLabel=data.appliance?.label
					def applianceTags=data.appliance?.tags
					def applianceCreated=data.appliance?.createdAt
					def applianceUpdated=data.appliance?.updatedAt
					                    
					sendEvent name: "verboseTrace", value:"getApplianceData>applianceId= ${applianceId}, applianceName=${applianceName}" +
						",applianceLabel=${applianceLabel},applianceTags=${applianceTags},created=${applianceCreated}, updated=${applianceUpdated}"
				}
				sendEvent name: "verboseTrace", value:"getApplianceData>done for applianceId=${applianceId}"
			} else {
				def message = resp.message
				def errors = resp.errors
				log.error "getApplianceData>status=${statusCode.toString()},message=${message},error=${errors} for applianceId=${applianceId}"
				sendEvent name: "verboseTrace", value:"getApplianceData>status=${statusCode.toString()},message=${message},error=${errors} for applianceId=${applianceId}"
			}                
		}  /* end api call */              
	} /* end while */
	def applianceDataJson=""
 
	if (data.appliance != []) {
    
		applianceDataJson = new groovy.json.JsonBuilder(data.appliance)
	}
/*    
	if (settings.trace) {
		log.debug "getApplianceData>applianceDataJson=${applianceDataJson}"
	}
*/    
	def applianceDataEvents = [
		applianceData: "${applianceDataJson.toString()}"
	]
/*    
	if (settings.trace) {
		log.debug "getApplianceData>applianceDataEvents to be sent= ${applianceDataEvents}"
	}
*/    
	generateEvent(applianceDataEvents)

}

//	locationId - Id of the location where the sensors are located
//		By default, the first location found in the user account
//	postData - indicates whether the data should be posted as event

void getApplianceList(locationId,postData='false') {
	def NEURIO_SUCCESS=200
	def appliancesData=[]
	def appliancesList=""
    
	locationId=determine_location_id(location_id)
	def statusCode=true
	int j=0        
	def bodyReq="locationId=" + locationId 
    
	while ((statusCode!= NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		api('appliancesList', bodyReq) {resp->

			statusCode= resp.status
			if (statusCode == NEURIO_SUCCESS) {
				if (settings.trace) {
					log.debug "getApplianceList>locationId=${locationId},resp data = ${resp.data}" 
				}
				data?.appliancesList=resp.data
				data.appliancesList.each {
					if (settings.trace) {
						def applianceId=it.id
						def applianceName= it?.name
						def applianceLabel= it?.label
						def applianceTags= it?.tags
						def applianceCreated=it?.createdAt
						def applianceUpdated=it?.updatedAt
						sendEvent name: "verboseTrace", value:"getApplianceList>locationId=${locationId},applianceId= ${applianceId}, applianceName=${applianceName}" +
							",applianceLabel=${applianceLabel},applianceTags=${applianceTags},created=${applianceCreated}, updated=${applianceUpdated}"
					}
					if (postData == 'true') {
						if (settings.trace) {
							log.debug "getApplianceList>adding ${data.appliances[i]} to appliancesData"
						}
						appliancesData << it  // to be transformed into Json later
					}
					appliancesList = appliancesList + it.id + ','                     
				} /* end each appliance */                
				sendEvent name: "verboseTrace", value:"getApplianceList>done for locationId=${locationId}"
			} else {
				def message = resp.message
				def errors = resp.errors
				log.error "getApplianceList>status=${statusCode.toString()},message=${message},error=${errors} for locationId=${locationId}"
				sendEvent name: "verboseTrace", value:"getApplianceList>status=${statusCode.toString()},message=${message},error=${errors} for locationId=${locationId}"
			}                
		}  /* end api call */              
	} /* end while */
	def appliancesDataJson=""
 
	if (appliancesData != []) {
    
		appliancesDataJson = new groovy.json.JsonBuilder(appliancesData)
	}
/*	
	if (settings.trace) {
		log.debug "getApplianceList>appliancesDataJson=${appliancesDataJson}"
	}
*/    
	def appliancesListEvents = [
		appliancesData: "${appliancesDataJson.toString()}",
		appliancesList: "${appliancesList.toString()}"
	]
/*    
	if (settings.trace) {
		log.debug "getApplianceList>appliancesListEvents to be sent= ${appliancesListEvents}"
	}
*/    
	generateEvent(appliancesListEvents)

}

//	locationId - Id of the location where the sensors are located
//		By default, the first location found in the user account
//	applianceId - Id of the appliance (not required)
//	start - start date for the stats generation
//	end - end date for the stats generation
//	granularity: in days,weeks, months, or unknown for the stats generation
//	minPower: min. Power for the events data extraction

void generateAppliancesStats(locationId,applianceId,start,end,granularity,minPower="") {
	def NEURIO_SUCCESS=200
	def appliancesStatsData=[]	
	def bodyReq    
    
	locationId=determine_location_id(location_id)

	// make sure start and granularity are defined
	if ((!start) || (!end) || (!granularity)) {
		log.error "generateAppliancesStats>start, end and granularity are required parameters"
		sendEvent name: "verboseTrace", value:"generateAppliancesStats>start, end and granularity are required parameters"
		return 
	}
	
	granularity=granularity?.trim()
	minPower = minPower?.trim()
    
	// make sure granularity is one of the correct values
	if ((granularity != 'unknown') && (granularity != 'days') && (granularity != 'weeks') && (granularity != 'months')) {
		log.error "generateAppliancesStats>only values of [days, weeks, months or unknown] are supported for granularity (${granularity})"
		sendEvent name: "verboseTrace", value: "only values of minutes, hours, days or months are supported for granularity (${granularity})"
		return 
	}
     
	if (applianceId) {
		bodyReq="applianceId=" + applianceId + "&start=" + ISOdateFormat(start) + "&end=" + ISOdateFormat(end)    	
    
	} else {
		bodyReq="locationId=" + locationId + "&start=" + ISOdateFormat(start) + "&end=" + ISOdateFormat(end)    	
	    
	}     
	bodyReq = bodyReq+ "&granularity=" + granularity    	
	if ((minPower != null) && (minPower != "")) {
		bodyReq = bodyReq+ "&minPower=" + minPower    	
	}
	int j=0        
	def statusCode=true

	while ((statusCode!= NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		api('appliancesStats', bodyReq) {resp->
			statusCode = resp.status        
			if (settings.trace) {
				log.debug "generateApplianceStats>locationId=${locationId},status=${resp.status},resp data = ${resp.data}" 
				sendEvent name: verboseTrace, value:"generateAppliancesStats>locationId=${locationId},status=${resp.status},resp data = ${resp.data}"                
			}                
			if (statusCode == NEURIO_SUCCESS) {
				if (resp.data !=[]) {            	
	 				data.stats=resp.data
					data.stats.each {
						appliancesStatsData << it
						applianceId= it.id
						if (settings.trace) {
							def applianceName= it?.name
							def applianceLabel= it?.label
							def applianceTags= it?.tags
							def applianceCreated= it?.createdAt
							def applianceUpdated= it?.updatedAt
							def applianceAvgPower= it?.averagePower 
							def applianceEventCount= it?.eventCount
							def applianceEnergy= it?.energy
							def statsStart= it?.start
							def statsEnd= it?.end
							sendEvent name: "verboseTrace", value:"generateAppliancesStats>locationId=${locationId},applianceId= ${applianceId}, applianceName=${applianceName}" +
								",applianceLabel=${applianceLabel},applianceTags=${applianceTags}.created=${applianceCreated},updated=${applianceUpdated}," +
								"applianceAvgPower=${applianceAvgPower},applianceEventCount=${applianceEventCount}," +
								"applianceEnergy=${applianceEnergy},statsStart=${statsStart},statsEnd=${statsEnd}" 
						}
					} /* end each stats */
				} 
				sendEvent name: "verboseTrace", value:"generateAppliancesStats>done for locationId=${locationId}"
			} else {
				def message = resp.message
				def errors = resp.errors
				log.error "generateAppliancesStats>status=${statusCode.toString()},message=${message},error=${errors} for locationId=${locationId}"
				sendEvent name: "verboseTrace", value:"generateAppliancesStats>status=${statusCode.toString()},message=${message},error=${errors} for locationId=${locationId}"
			} /* end if statusCode */               
		}  /* end api call */              
	} /* end while */

	def appliancesStatsJson=""
 
	if (appliancesStatsData != []) {
    
		appliancesStatsJson = new groovy.json.JsonBuilder(appliancesStatsData)
	}
/*	
	if (settings.trace) {
		log.debug "generateAppliancesStats>appliancesStatsJson=${appliancesStatsJson}"
	}
*/    
	def appliancesStatsEvents = [
		appliancesStatsData: "${appliancesStatsJson.toString()}"
	]
/*    
	if (settings.trace) {
		log.debug "generateAppliancesStats>appliancesStatsEvents to be sent= ${appliancesStatsEvents}"
	}
*/    
	generateEvent(appliancesStatsEvents)
}

//	locationId - Id of the location where the sensors are located
//		By default, the first location found in the user account
//	applianceId - Id of the appliance (not required)
//	start - start date for the stats generation
//	end - end date for the stats generation
//	minPower: min. Power for the events data extraction

void generateAppliancesEvents(locationId,applianceId,start,end,minPower="") {
	def NEURIO_SUCCESS=200
	def appliancesEventsData=[]
	def bodyReq
    
	locationId=determine_location_id(location_id)
	minPower = minPower?.trim()
    
	// make sure start and end are defined
	if ((!start) || (!end)) {
		log.error "generateAppliancesEvents>start and end are required parameters"
		sendEvent name: "verboseTrace", value:"generateAppliancesStats>start and end are required parameters"
		return 
	}
	
	if (applianceId) {
		bodyReq="applianceId=" + applianceId + "&start=" + ISOdateFormat(start) + "&end=" + ISOdateFormat(end)    	
    
	} else {
		bodyReq="locationId=" + locationId + "&start=" + ISOdateFormat(start) + "&end=" + ISOdateFormat(end)    	
	    
	}     
	if ((minPower != null) && (minPower != "")) {
		bodyReq = bodyReq+ "&minPower=" + minPower    	
	}
	int j=0        
	def statusCode=true
    
	while ((statusCode!= NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		api('appliancesEvents', bodyReq) {resp->
			statusCode = resp.status        
			if (settings.trace) {
				log.debug "generateAppliancesEvents>locationId=${locationId},status=${resp.status},resp data = ${resp.data}" 
				sendEvent name: verboseTrace, value:"generateAppliancesEvents>locationId=${locationId},status=${resp.status},resp data = ${resp.data}"                
			}                
			if (statusCode == NEURIO_SUCCESS) {
				if (resp.data !=[]) {            	
 					data.events=resp.data
					data.events.each {	
						appliancesEventsData << it
						applianceId= it.appliance.id
						if (settings.trace) {
							def applianceName= it.appliance?.name
							def applianceLabel= it.appliance?.label
							def applianceTags= it.appliance?.tags
							def applianceCreated= it.appliance?.createdAt
							def applianceUpdated= it.appliance?.updatedAt
							def applianceAvgPower= it?.averagePower 
							def applianceEventGuesses= it?.guesses
							def applianceEnergy= it?.energy
							def eventStart= it?.start
							def eventEnd= it?.end
							def eventStatus= it?.status
							def eventIsConfirmed= it?.isConfirmed
							sendEvent name: "verboseTrace", value:"generateAppliancesEvents>locationId=${locationId},applianceId= ${applianceId}, applianceName=${applianceName}" +
								",applianceLabel=${applianceLabel},applianceTags=${applianceTags},created=${applianceCreated},updated=${applianceUpdated}," +
								"applianceAvgPower=${applianceAvgPower},applianceEventGuesses=${applianceEventGuesses}," +
								"applianceEnergy=${applianceEnergy},eventStart=${eventStart},eventEnd=${eventEnd}" +
								"eventStatus=${eventStatus},eventIsConfirmed=${eventIsConfirmed}" 
						}                                
					} /* end each event */
				} 
				sendEvent name: "verboseTrace", value:"generateAppliancesEvents>done for locationId=${locationId}"
			} else {
				def message = resp.message
				def errors = resp.errors
				log.error "generateAppliancesEvents>status=${statusCode.toString()},message=${message},error=${errors} for locationId=${locationId}"
				sendEvent name: "verboseTrace", value:"generateAppliancesEvents>status=${statusCode.toString()},message=${message},error=${errors} for locationId=${locationId}"
			} /* end if statusCode */               
		}  /* end api call */              
	} /* end while */

	def appliancesEventsJson=""
 
	if (appliancesEventsData != []) {
    
		appliancesEventsJson = new groovy.json.JsonBuilder(appliancesEventsData)
	}
/*	
	if (settings.trace) {
		log.debug "generateAppliancesEvents>appliancesEventsJson=${appliancesEventsJson}"
	}
*/    
	def appliancesEvents = [
		appliancesEventsData: "${appliancesEventsJson.toString()}"
	]
/*    
	if (settings.trace) {
		log.debug "generateAppliancesEvents>appliancesEvents to be sent= ${appliancesEvents}"
	}
*/    
	generateEvent(appliancesEvents)
}

void getCurrentUserInfo() {
	def NEURIO_SUCCESS=200

	def statusCode=true
	int j=0        
	while ((statusCode != NEURIO_SUCCESS) && (j++ <2)) { // retries once if api call fails

		if (settings.trace) {
			log.debug "getCurrentUserInfo>about to call api"
		}
		api('currentUserInfo', null) {resp ->
			statusCode = resp.status        
			if (statusCode == NEURIO_SUCCESS) {
        			data?.user=resp.data
				if (settings.trace) {
					log.debug "getCurrentUserInfo>resp data = ${resp.data}" 
                
				}
				def userid = data.user.id
				def username = data.user.name
				def email = data.user.email
				def active = data.user.status  
				if (status != 'active') {
					if (settings.trace) {
						sendEvent name: "verboseTrace", value: "getCurrentUserInfo>userId=${userid},name=${username},email=${email} not active, exiting..."
					}
					return
				}
                
				if (settings.trace) {
					sendEvent name: "verboseTrace", value: "getCurrentUserInfo>userId=${userId},name=${username},email=${email},active=${active}"
				}
				data.user?.locationsCount = resp.data.locations.size()
				data.user.locations.each {
					def locationId = it.id
					def locationName = it.name
					def timezone = it.timezone
					if (settings.trace) {
						sendEvent name: "verboseTrace", value: "getCurrentUserInfo>found locationId=${locationId},name=${locationName},timezone=${timezone}"
					}
					it?.sensorsCount=it.sensors.size()
					it.sensors.each {
						def sensorId = it.id
						def sensorType = it.sensorType
						if (settings.trace) {
							sendEvent name: "verboseTrace", value: "getCurrentUserInfo>sensorId=${sensorId},type=${sensorType}"
						}
						it?.ChannelsCount=it.channels.size()
						it.channels.each {
							def channelId = it.channel
							def channelName = it.name
							def channelType = it.channelType
							if (settings.trace) {
								sendEvent name: "verboseTrace", value: "getCurrentUserInfo>ChannelId=${channelId},name=${channelName},type=${channelType}"
							}
                            
						} /* end each channel */                        
					} /* end each sensor */                        
				} /* end each location */                        
				sendEvent name: "verboseTrace", value:"getCurrentUserInfo>done"
			} else {
				statusCode=data.status
				def message = data.message
				def errors = data.errors
				log.error "getCurrentUserInfo>status=${statusCode.toString()},message=${message},error=${errors}"
				sendEvent name: "verboseTrace", value:"getCurrentUserInfo>status=${statusCode.toString()},message=${message},error=${errors}"
			} /* end if statusCode */                
		}  /* end api call */              
	} /* end while */
}


private def refresh_tokens() {
        
	def method = 
	[
		headers: [
			'Content-Type': "application/json",
			'charset': "UTF-8"
			],
		uri: "${get_URI_ROOT()}/oauth2/token?", 
		body: toQueryString([grant_type:"refresh_token",client_id:get_appKey(),client_secret:get_refresh_token()])
	]
	if (settings.trace) {
		log.debug "refresh_tokens> uri = ${method.uri}"
	}
	def successRefreshTokens = {resp ->
		if (settings.trace) {
			log.debug "refresh_tokens> response = ${resp.data}"
		}
		data.auth.access_token = resp.data.access_token
		data.auth.refresh_token = resp.data.refresh_token
		data.auth.expires_in = resp.data.expires_in
		data.auth.token_type = resp.data.token_type
		data.auth.scope = resp.data.scope
	}
	try {
		httpPostJson(method, successRefreshTokens)
	} catch (java.net.UnknownHostException e) {
		log.error "refresh_tokens> Unknown host - check the URL " + method.uri
		sendEvent name: "verboseTrace", value: "refresh_tokens> Unknown host"
		return false
	} catch (java.net.NoRouteToHostException t) {
		log.error "refresh_tokens> No route to host - check the URL " + method.uri
		sendEvent name: "verboseTrace", value: "refresh_tokens> No route to host"
		return false
	} catch (java.io.IOException e) {
		log.error "refresh_tokens> Authentication error, neurio servers cannot be reached at "
		sendEvent name: "verboseTrace", value: "refresh_tokens> Auth error"
		return false
	} catch (any) {
		log.error "refresh_tokens> general error " + method.uri
		sendEvent name: "verboseTrace", value:
			"refresh_tokens> general error at ${method.uri}"
		return false
	}
	def authexptime = new Date((now() + (data.auth.expires_in * 60 * 1000))).getTime()
	data.auth.authexptime = authexptime

	if (data.auth.sensorId) {		// Created by initalSetup, need to refresh Parent tokens and other children
		refreshParentTokens()
	}        
	if (settings.trace) {
		log.debug "refresh_tokens> data_auth.expires_in in time = ${authexptime}"
		sendEvent name: "verboseTrace", value:
			"refresh_tokens>expire in ${data.auth.expires_in} minutes"
	}
	return true
}

void refreshChildTokens(auth) {
	if (settings.trace) {
		log.debug "refreshChildTokens>begin token auth= $auth"
	}
	data.auth.access_token = auth.authToken
	data.auth.refresh_token = auth.refreshToken
	data.auth.expires_in = auth.expiresIn
	data.auth.token_type = auth.tokenType
	data.auth.scope = auth.scope
	data.auth.authexptime = auth.authexptime
	if (settings.trace) {
		log.debug "refreshChildTokens>end data.auth=$data.auth"
	}
}

private void refreshParentTokens() {
	if (settings.trace) {
		log.debug "refreshParentTokens>begin data.auth = ${data.auth}"
	}
	if (settings.trace) {
		log.debug "refreshParentTokens>auth=$auth, about to call parent.setParentAuthTokens"
	}         
	parent.setParentAuthTokens(data.auth)
	if (settings.trace) {
		log.debug "refreshParentTokens>end"
	}         
}

private void login() {
	if (data?.auth.sensorId) {
    	// Created by initalSetup
		if (settings.trace) {
			log.debug "login> about to call refreshThisChildAuthTokens"
		}
		parent.refreshThisChildAuthTokens(this)
	} else { 
		if (settings.trace) {
			log.debug "login> about to call setAuthTokens, data auth=$data?.auth"
		}
		setAuthTokens()
	}    
	if (!isLoggedIn()) {
		if (settings.trace) {
			log.debug "login> no auth_token..., failed"
		}
		return
	}
}



void setAuthTokens() {
	def method = 
	[
		headers: [
			'charset': "UTF-8",
			'Content-Type': "application/x-www-form-urlencoded"
			],
		uri: "${get_URI_ROOT()}/oauth2/token?",
		body: toQueryString([grant_type:"client_credentials",client_id:get_appKey(),client_secret:get_client_secret()])
	]
	def successTokens = {resp ->
		if (settings.trace) {
			log.debug "setAuthTokens> resp data= ${resp.data}"
		}
        
        
		data?.auth = resp.data
		data.auth.access_token = resp.data.access_token
		data.auth.expires_in = resp.data.expires_in
		data.auth.token_type = resp.data.token_type
		if (settings.trace) {
			log.debug "setAuthTokens> accessToken= ${data.auth.access_token}," +
				"tokenType=${data.auth.token_type}"
		}
	}
	try {
		httpPostJson(method, successTokens)

	} catch (java.net.UnknownHostException e) {
		log.error "setAuthTokens> Unknown host - check the URL " + method.uri
		sendEvent name: "verboseTrace", value: "setAuthTokens> Unknown host " +
			method.uri
		return
	} catch (java.net.NoRouteToHostException t) {
		log.error "setAuthTokens> No route to host - check the URL " + method.uri
		sendEvent name: "verboseTrace", value: "setAuthTokens> No route to host" +
			method.uri
		return
	} catch (java.io.IOException e) {
		log.error "setAuthTokens> Auth error, neurio servers cannot be reached at " +
			method.uri
		sendEvent name: "verboseTrace", value: "setAuthTokens> Auth error " +
			method.uri
		return
	}	        

	// determine token's expire time
	def authexptime = new Date((now() + (data.auth.expires_in * 60 * 1000))).getTime()
	data.auth.authexptime = authexptime
	if (settings.trace) {
		log.debug "setAuthTokens> expires in ${data.auth.expires_in} minutes"
		log.debug "setAuthTokens> data_auth.expires_in in time = ${authexptime}"
		sendEvent name: "verboseTrace", value:
			"setAuthTokens>expire in ${data.auth.expires_in} minutes"
	}
}
private def isLoggedIn() {
	if (data.auth == null) {
		if (settings.trace) {
			log.debug "isLoggedIn> no data auth"
		}
		return false
	} else {
		if (data.auth.access_token == null) {
			if (settings.trace) {
				log.debug "isLoggedIn> no access token"
				return false
			}
		}
	}
	return true
}
private def isTokenExpired() {
	def buffer_time_expiration=5   // set a 5 min. buffer time before token expiration to avoid auth_err 
	def time_check_for_exp = now() + (buffer_time_expiration * 60 * 1000);
	if (settings.trace) {
		log.debug "isTokenExpired> check expires_in: ${data.auth.authexptime} > time check for exp: ${time_check_for_exp}"
	}
	if (data.auth.authexptime > time_check_for_exp) {
		if (settings.trace) {
			log.debug "isTokenExpired> not expired"
		}
		return false
	}
	if (settings.trace) {
		log.debug "isTokenExpired> expired"
	}
	return true
}

// Determine id from argument or default value (first location found)

private def determine_location_id(location_id) {
	def locationId
    
	if (settings.trace) {
		log.debug "determine_location_id>begin locationId= ${locationId}"
	}
	if ((location_id != null) && (location_id != "")) {
		locationId = location_id.trim()
	} else {
		locationId=data?.user?.locations[0]?.id
		if (settings.trace) {
			log.debug "determine_location_id> locationId from data.locationId= ${locationId}"
		}
	}
	if (settings.trace) {
		log.debug "determine_location_id>end locationId= ${locationId}"
	}
	return locationId
}


// Determine id from settings or initalSetup
private def determine_sensor_id(sensor_id) {
	def sensorId
    
	if ((sensor_id != null) && (sensor_id != "")) {
		sensorId = sensor_id.trim()
	} else if ((settings.sensorId != null) && (settings.sensorId  != "")) {
		sensorId = settings.sensorId.trim()
		if (settings.trace) {
			log.debug "determine_sensor_id> sensorId = ${settings.sensorId}"
		}
	} else if (data?.auth?.sensorId != null) {
    
		settings.appKey = get_appKey() 
		settings.sensorId = data.auth.sensorId
		sensorId=settings.sensorId
		if (settings.trace) {
			log.debug "determine_sensor_id> sensorId from data.auth= ${sensorId}"
		}
    
	} else  {
		settings.sensorId = data.user.locations[0].sensors[0].id
		sensorId=settings.sensorId
		if (settings.trace) {
			log.debug "determine_sensor_id> sensorId from data.user.locations[0].sensors[0]= ${sensorId}"
		}
	}
	return sensorId
}

// Get the appKey for authentication
private def get_appKey() {
	return settings.appKey
}    
// Get the client secret
private def get_client_secret() {
	return settings.privateKey
}    


// Get the client's refresh token

private def get_refresh_token() {
	return data.auth.refresh_token
}    

// Called by My Neurio Init for initial creation of a child Device
void initialSetup(device_client_id, auth_data, device_neurio_id) {
/*
	settings.trace='true'
*/	
	if (settings.trace) {
		log.debug "initialSetup>begin"
		log.debug "initialSetup> device_neurio_Id = ${device_neurio_id}"
		log.debug "initialSetup> device_client_id = ${device_client_id}"
	}	
	settings.appKey= device_client_id
	settings.sensorId = device_neurio_id
	data?.auth = settings    
	data.auth.access_token = auth_data.authToken
	data.auth.expires_in = auth_data.expiresIn
	data.auth.token_type = auth_data.tokenType
	data.auth.authexptime= auth_data?.authexptime
    
	if (settings.trace) {
		log.debug "initialSetup> settings = $settings"
		log.debug "initialSetup> data_auth = $data.auth"
		log.debug "initialSetup>end"
	}
	getCurrentUserInfo()
	poll()
}

def toQueryString(Map m) {
	return m.collect { k, v -> "${k}=${URLEncoder.encode(v.toString())}" }.sort().join("&")
}

private def get_URI_ROOT() {
	return "https://api.neur.io/v1"
}
