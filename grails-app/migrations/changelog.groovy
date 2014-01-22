databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1390358168160-1") {
		createTable(tableName: "cfdi") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cfdiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cadena_original", type: "longtext")

			column(name: "comentario", type: "varchar(355)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descuentos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "emisor", type: "varchar(600)") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "folio", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "origen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "receptor", type: "varchar(600)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "serie", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "subtotal", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "timbrado", type: "datetime")

			column(name: "tipo", type: "varchar(12)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_de_cfdi", type: "varchar(1)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(255)")

			column(name: "uuid", type: "varchar(300)")

			column(name: "xml", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "xml_name", type: "varchar(200)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-2") {
		createTable(tableName: "cliente") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "clientePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "direccion_calle", type: "varchar(200)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_codigo_postal", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_colonia", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_estado", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_municipio", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_numero_exterior", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_numero_interior", type: "varchar(50)")

			column(name: "direccion_pais", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-3") {
		createTable(tableName: "empresa") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "empresaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "certificado_digital", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "certificado_digital_pfx", type: "mediumblob")

			column(name: "clave", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "direccion_calle", type: "varchar(200)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_codigo_postal", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_colonia", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_estado", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_municipio", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_numero_exterior", type: "varchar(50)") {
				constraints(nullable: "false")
			}

			column(name: "direccion_numero_interior", type: "varchar(50)")

			column(name: "direccion_pais", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "llave_privada", type: "mediumblob") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_certificado", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "password_pfx", type: "varchar(255)")

			column(name: "regimen", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar(13)") {
				constraints(nullable: "false")
			}

			column(name: "xml_directory", type: "varchar(250)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-4") {
		createTable(tableName: "producto") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "productoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "clave", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_predial", type: "varchar(200)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "unidad", type: "varchar(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-5") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-6") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-7") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-8") {
		createTable(tableName: "venta") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ventaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(300)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descuentos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_pago", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuestos", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "moneda", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pagos_aplicados", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "plazo", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "saldo", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "subtotal", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tc", type: "decimal(19,4)") {
				constraints(nullable: "false")
			}

			column(name: "tipo", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "vencimiento", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-9") {
		createTable(tableName: "venta_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "venta_detPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cantidad", type: "decimal(19,3)") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar(300)")

			column(name: "costo", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "descuento", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "impuesto_tasa", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "precio", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "producto_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "subtotal", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "venta_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "partidas_idx", type: "integer")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-10") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-18") {
		createIndex(indexName: "clave_uniq_1390358168077", tableName: "cliente", unique: "true") {
			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-19") {
		createIndex(indexName: "nombre_uniq_1390358168081", tableName: "cliente", unique: "true") {
			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-20") {
		createIndex(indexName: "clave_uniq_1390358168083", tableName: "empresa", unique: "true") {
			column(name: "clave")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-21") {
		createIndex(indexName: "nombre_uniq_1390358168085", tableName: "empresa", unique: "true") {
			column(name: "nombre")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-22") {
		createIndex(indexName: "FKC42BD160A8E83519", tableName: "producto") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-23") {
		createIndex(indexName: "authority_uniq_1390358168091", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-24") {
		createIndex(indexName: "username_uniq_1390358168093", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-25") {
		createIndex(indexName: "FK143BF46A5B4E03D9", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-26") {
		createIndex(indexName: "FK143BF46AB6233FF9", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-27") {
		createIndex(indexName: "FK6AE6A4C48B97379", tableName: "venta") {
			column(name: "cliente_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-28") {
		createIndex(indexName: "FK6AE6A4CA8E83519", tableName: "venta") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-29") {
		createIndex(indexName: "FKD9C517A07AE12DF9", tableName: "venta_det") {
			column(name: "venta_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-30") {
		createIndex(indexName: "FKD9C517A0DAE799B", tableName: "venta_det") {
			column(name: "producto_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-11") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "producto", constraintName: "FKC42BD160A8E83519", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-12") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46AB6233FF9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A5B4E03D9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-14") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "venta", constraintName: "FK6AE6A4C48B97379", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-15") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "venta", constraintName: "FK6AE6A4CA8E83519", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-16") {
		addForeignKeyConstraint(baseColumnNames: "producto_id", baseTableName: "venta_det", constraintName: "FKD9C517A0DAE799B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "producto", referencesUniqueColumn: "false")
	}

	changeSet(author: "rcancino (generated)", id: "1390358168160-17") {
		addForeignKeyConstraint(baseColumnNames: "venta_id", baseTableName: "venta_det", constraintName: "FKD9C517A07AE12DF9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta", referencesUniqueColumn: "false")
	}

	include file: 'CfdiFolio-model.groovy'
}
