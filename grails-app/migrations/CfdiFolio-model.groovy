databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1390399977305-1") {
		addColumn(tableName: "cfdi_folio") {
			column(name: "emisor", type: "varchar(13)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-2") {
		dropIndex(indexName: "serie", tableName: "cfdi_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-3") {
		createIndex(indexName: "emisor_uniq_1390399976922", tableName: "cfdi_folio", unique: "true") {
			column(name: "emisor")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-4") {
		createIndex(indexName: "folio_uniq_1390399976923", tableName: "cfdi_folio", unique: "true") {
			column(name: "folio")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-5") {
		dropColumn(columnName: "ano_aprobacion", tableName: "cfdi_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-6") {
		dropColumn(columnName: "asignacion", tableName: "cfdi_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-7") {
		dropColumn(columnName: "folio_final", tableName: "cfdi_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-8") {
		dropColumn(columnName: "folio_inicial", tableName: "cfdi_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-9") {
		dropColumn(columnName: "numero_de_aprobacion", tableName: "cfdi_folio")
	}

	changeSet(author: "rcancino (generated)", id: "1390399977305-10") {
		dropColumn(columnName: "rfc", tableName: "cfdi_folio")
	}
}
