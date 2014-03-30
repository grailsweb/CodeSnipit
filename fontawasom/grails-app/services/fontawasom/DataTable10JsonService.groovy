package fontawasom

import grails.transaction.Transactional
import grails.converters.JSON

@Transactional
class DataTable10JsonService {
	def process(params, domain){
		def dataToRender = [:]
		def gSearchMap  = [:]
		def cSearchMap = [:]
		def gSearchFld = new ArrayList()
		def cSearchFld = new ArrayList()
		
		int len= params.findAll{ it.key ==~ /columns\[\d+\]\[data\]/ }.size()

		def limits =  [max:  params.length ? params['length'] as int :10 ,  offset:  params.start ? params['start'] as int : 0]
		def ord= order(params,len)
		def where =''
		
		// global search
		if ( params."search[value]"?.trim().length() > 0  ) {
			for ( int i=0; i<len ; i++ ) {
				if ( params."columns[${i}][searchable]"?.equalsIgnoreCase('true') ) {
					gSearchFld.add(  params."columns[${i}][data]" + " LIKE :"+ params."columns[${i}][data]" + " ")
					gSearchMap[ params."columns[${i}][data]"]= "%"+params."search[value]"+"%"
				}
			}
		}
		// Individual column filtering  eg. columns[0][search][value] =jo
		for ( int i=0; i<len ; i++ ) {
			if (params."columns[${i}][searchable]"?.equalsIgnoreCase('true') && params."columns[${i}][search][value]"?.trim().length()> 0 ) {
				cSearchFld.add(  params."columns[${i}][data]" + " LIKE :col_"+ params."columns[${i}][data]" + " ")
				cSearchMap[ "col_"+params."columns[${i}][data]"]= "%"+params."columns[${i}][search][value]"+"%"
			}
		}
		if ( gSearchFld?.size() > 0 ){
			where = " where " + gSearchFld.join(' OR ')
		}
		if( cSearchFld?.size()>0 ) {
			where = (where == '') ? " where " + cSearchFld.join(' AND '):	where +' AND ('+cSearchFld.join(' AND ') +')'
		}
		// merge Map
		gSearchMap << cSearchMap
		
		def data = new ArrayList()
		def query = new StringBuilder("from ${domain.name} as t ${where} ");
		try {
			def countquery ="SELECT COUNT(*) "+ query
			query.append(" ${ord} ")
			/*
			 * println "$ord "
			 * println "where " + where
			println "map " + gSearchMap
			println query
			println "count query " + countquery*/
			def recordsTotal = domain.count()
			def recordsFiltered = domain.executeQuery(countquery ,gSearchMap)[0]

			def rows = domain.findAll(query.toString(),gSearchMap,limits)
			rows?.each { row ->
				//println row.dump()
				def returnRow = [:]
				for(int i=0; i<len; i++) {
					def str="columns["+i+"][data]"
					returnRow[params."$str"] = row[params."$str"]
				}
				data << returnRow
			}

			dataToRender.draw =params.draw
			dataToRender.recordsTotal=recordsTotal
			dataToRender.recordsFiltered=recordsFiltered
			dataToRender.data =data
			//println dataToRender as JSON;
		} catch( e){
			println e
		}
		return dataToRender as JSON;
	}
	def order ( params, len )	{
		def order = '';
		int colrow = params."order[0][column]" as int
		order ='ORDER BY '+params."columns[${colrow}][data]"
		order += " " + (params."order[0][dir]"?.equalsIgnoreCase("asc")?' ASC':' DESC')
		return order
	}
	def filter ( params, len)	{
		def globalSearch = new ArrayList()
		def columnSearch = new ArrayList()
		def gSearchMap  = [:]
		def cSearchMap = [:]
		def gSearchFld = new ArrayList()
		def cSearchFld = new ArrayList()

		def str='search[value]'
		if ( params."search[value]"?.trim().length() > 0  ) {
			for ( int i=0; i<len ; i++ ) {
				//	println   "columns[${i}][searchable] == > "+  params."columns[${i}][searchable]"
				if ( params."columns[${i}][searchable]"?.equalsIgnoreCase('true') ) {
					//	globalSearch.add(  params."columns[${i}][data]" + " LIKE '%"+params."search[value]"+"%' ")

					gSearchFld.add(  params."columns[${i}][data]" + " LIKE :"+ params."columns[${i}][data]" + " ")
					gSearchMap[ params."columns[${i}][data]"]= "%"+params."search[value]"+"%"
				}
			}
		}

		//columns[0][search][value] =jo
		// Individual column filtering
		for ( int i=0; i<len ; i++ ) {
			//def cstr =params."columns[${i}][search][value]"

			if (params."columns[${i}][searchable]"?.equalsIgnoreCase('true') && "columns[${i}][search][value]"?.trim().length()> 0 ) {
				/*	def col = "columns[${i}][data]"
				 columnSearch.add( params."$col" + " LIKE '%${cstr}%' ")*/

				cSearchFld.add(  params."columns[${i}][data]" + " LIKE :col_"+ params."columns[${i}][data]" + " ")
				cSearchMap[ "col_"+params."columns[${i}][data]"]= "%"+"columns[${i}][search][value]"+"%"
			}
		}
		return
		println "columnSearch " + columnSearch
		// Combine the filters into a single string
		def where = ''
		if ( globalSearch.size() > 0 ) {
			where = ' ('+globalSearch.join(' OR ')+' )'
		}

		if ( columnSearch.size()>0 )  {
			where = (where == '') ?columnSearch.join(' AND '):		where +' AND ('+columnSearch.join(' AND ') +')'
		}

		if ( where != '' ) {
			where = 'WHERE '+where
		}

		return where

	}


	

}
