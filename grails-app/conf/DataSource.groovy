dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = 'update' // one of 'create', 'create-drop', 'update', 'validate', ''
			url = 'jdbc:mysql://localhost/mobix?autoReconnect=true'
			//url = 'jdbc:mysql://10.10.1.228/mobix'
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
			username = 'root'
			password = 'sys'
			pooled = true
			properties {
			   maxActive = 2
			   maxIdle = 2
			   initialSize = 2
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   maxWait = 10000
			}
        }
		
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
            dbCreate = ""
			dialect = org.hibernate.dialect.MySQLInnoDBDialect
			driverClassName = 'com.mysql.jdbc.Driver'
			username = 'root'
			password = 'sys'
			url = 'jdbc:mysql://10.10.1.228/mobix?autoReconnect=true'
			pooled = true
			properties {
			   maxActive = 3
			   maxIdle = 2
			   initialSize = 2
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   validationQuery = "/* ping */"
			   maxWait = 10000
			   
			}
        }
    }
}
