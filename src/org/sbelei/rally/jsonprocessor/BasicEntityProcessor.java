package org.sbelei.rally.jsonprocessor;

import com.rallydev.rest.*;
import org.sbelei.rally.domain.BasicEntity;

public class BasicEntityProcessor extends EntityProcessor<BasicEntity> {

    public BasicEntityProcessor(RallyRestApi restApi) {
        super(restApi);
    }

    @Override
	public BasicEntity newEntity() {
		return new BasicEntity();
	}

}
