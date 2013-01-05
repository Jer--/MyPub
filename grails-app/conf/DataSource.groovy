/*******************************************************************************
 * Author : Group BBHC
 * License : AGPL v3
 ******************************************************************************/
dataSource {
	pooled = false
	driverClassName = "com.mysql.jdbc.Driver"
	username = "mypub"
	password = "ivvq2012"
}
hibernate {
	cache.use_second_level_cache=true
	cache.use_query_cache=true
	cache.provider_class='org.hibernate.cache.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://91.236.239.60/mypub"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://91.236.239.60/mypub"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://91.236.239.60/mypub"
		}
	}
}
