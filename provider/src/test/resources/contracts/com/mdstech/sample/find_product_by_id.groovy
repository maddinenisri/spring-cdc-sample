import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "should return product by id=1"

	request {
		url "/product/1"
		method GET()
	}

	response {
		status OK()
		headers {
			contentType applicationJson()
		}
		body (
			id: 1,
			name: "30 Year Fixed"
		)
	}
}