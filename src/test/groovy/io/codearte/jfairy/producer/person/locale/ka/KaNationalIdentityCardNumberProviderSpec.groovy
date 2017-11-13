package io.codearte.jfairy.producer.person.locale.ka

import io.codearte.jfairy.producer.BaseProducer
import io.codearte.jfairy.producer.RandomGenerator
import spock.lang.Specification

class KaNationalIdentityCardNumberProviderSpec extends Specification {
	private KaNationalIdentityCardNumberProvider provider

	def setup() {
		BaseProducer baseProducer = new BaseProducer(new RandomGenerator())
		provider = new KaNationalIdentityCardNumberProvider(baseProducer)
	}

	private boolean isOldIdCardNumber(String id) {
		id.matches("N[ა-ჰ]\\d{7}")
	}

	private boolean isNewIdCardNumber(String id) {
		id.matches("\\d{2}\\p{Upper}{2}\\d{5}")
	}

	def isIdCardNumber(String id) {
		isNewIdCardNumber(id) || isOldIdCardNumber(id)
	}

	def "Should always generate proper nationalIdentityCardNumber"() {
		expect:
		isIdCardNumber(provider.get())
		where:
		i << (1..100)
	}
}
