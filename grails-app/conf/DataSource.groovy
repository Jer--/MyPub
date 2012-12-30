/*******************************************************************************
 * Author : Group BBHC
 * Licence : AGPL v3
 ******************************************************************************/
dataSource {
	//	pooled = true
	//	driverClassName = "org.h2.Driver"
	//	username = "sa"
	//	password = ""
	//}
	//hibernate {
	//	cache.use_second_level_cache = true
	//	cache.use_query_cache = false
	//	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
	//}
	//// environment specific settings
	//environments {
	//	development {
	//		dataSource {
	//			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
	//			url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
	//		}
	//	}
	//	test {
	//		dataSource {
	//			dbCreate = "update"
	//			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
	//		}
	//	}
	//
	//	production {
	//		dataSource {
	//			dbCreate = "update"
	//			url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
	//			pooled = true
	//			properties {
	//				maxActive = -1
	//				minEvictableIdleTimeMillis=1800000
	//				timeBetweenEvictionRunsMillis=1800000
	//				numTestsPerEvictionRun=3
	//				testOnBorrow=true
	//				testWhileIdle=true
	//				testOnReturn=true
	//				validationQuery="SELECT 1"
	//			}
	//		}
	//	}

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
