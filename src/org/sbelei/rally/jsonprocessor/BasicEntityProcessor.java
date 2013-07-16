package org.sbelei.rally.jsonprocessor;

import org.sbelei.rally.domain.BasicEntity;

public class BasicEntityProcessor extends EntityProcessor<BasicEntity> {

	@Override
	public BasicEntity newEntity() {
		return new BasicEntity();
	}

}
