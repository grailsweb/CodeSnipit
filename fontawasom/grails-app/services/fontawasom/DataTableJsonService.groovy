package fontawasom

import grails.transaction.Transactional
import grails.converters.JSON
@Transactional
class DataTableJsonService {
	// create-service fontawasom.DataTable10JsonService
	def process(params, domain, restrictions, returnFields) {
		def dataToRender = [:];
		def filters = []
		def columnfilters = []
		// Gloabl filter filed selected in the client side
		for(int i=0; i<params.iColumns; i++) {
			if(params['bSearchable_' + i] == "true") {
				filters << "${params['mDataProp_' + i].replaceAll("->", ".")} like :filter"
			}
		}
		def filter = filters.join(" OR ")

		// column filter for all fileds
		for(int i=0; i<params.iColumns; i++) {
			if(params['sSearch_' + i]?.trim()?.length() > 0 ) {
				columnfilters << " ${params['mDataProp_' + i].replaceAll("->", ".")} like '%" +params['sSearch_' + i]?.trim() +"%' "
			}
		}

		if( columnfilters?.size() > 0 ) {
			if( restrictions?.trim()?.length() <= 0 ){
				restrictions = " ("+ columnfilters.join( " AND ") + ") "
			}else{
				restrictions += " and (" +  columnfilters.join( " AND ") + ") "
			}
		}

		dataToRender.sEcho = params.sEcho;
		dataToRender.aaData=[];

		def query
		if(restrictions == "") {
			query = new StringBuilder("from ${domain.name} as t");
			if(params.sSearch) {
				query.append(" WHERE  (${filter})");
			}
			dataToRender.iTotalRecords = domain.count();
		} else {
			query = new StringBuilder("from ${domain.name} WHERE ${restrictions}");
			if(params.sSearch) { // Con parametro de busqueda
				query.append(" AND (${filter})");
			}
			dataToRender.iTotalRecords = domain.executeQuery("SELECT COUNT(*) FROM ${domain.name} WHERE ${restrictions}")[0]
		}

		dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords;

		def sortDir = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc';
		int sortCol = (params?.iSortCol_0)?((params.iSortCol_0.isNumber())?params.iSortCol_0.toInteger():0):0;
		def sortProperty = params['mDataProp_' + sortCol];
		query.append(" ORDER BY ${sortProperty} ${sortDir}");
		//println query
		def rows = [];

		if ( params.sSearch ) {
			def countQuery
			if(restrictions == "") {
				countQuery = new StringBuilder("SELECT COUNT(*) FROM ${domain.name} WHERE " )
			} else {
				countQuery = new StringBuilder("SELECT COUNT(*) FROM ${domain.name} WHERE ${restrictions} AND" )
			}

			countQuery.append(" (${filter})")
			def result = domain.executeQuery(countQuery.toString(), [filter: "%${params.sSearch}%"])

			if ( result ) {
				dataToRender.iTotalDisplayRecords = result[0]
			}
			println "filter :" + query.toString()
			println  "%${params.sSearch}%"
			rows = domain.findAll(query.toString(), [filter: "%${params.sSearch}%"],
			[max: params.iDisplayLength as int,
				offset: params.iDisplayStart as int])
		} else {
			int ma = params.iDisplayLength ? params.iDisplayLength as int : 10;
			int off = params.iDisplayStart ? params.iDisplayStart as int : 0;
			println query.toString()
			rows = domain.findAll(query.toString(), [max: ma ,  offset: off])
		}

		rows?.each { row ->
			def returnRow = [:]
			for(int i=0; i<params.iColumns.toInteger(); i++) {
				if(returnFields[params['mDataProp_' + i]]) { //Custom
					returnRow[params['mDataProp_' + i]] = Eval.me('row', row, returnFields[params['mDataProp_' + i]])
				} else {
					returnRow[params['mDataProp_' + i]] = Eval.me('row', row, 'row.' + params['mDataProp_' + i].replaceAll("->", "."))
				}
			}
			dataToRender.aaData << returnRow
		}

		return dataToRender as JSON;

	}

	// source taken from https://github.com/nafiux/grails-plugin-datatablejson
}
