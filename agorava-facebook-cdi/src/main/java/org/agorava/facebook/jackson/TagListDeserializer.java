/*
 * Copyright 2013 Agorava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.agorava.facebook.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.agorava.api.atinject.BeanResolver;
import org.agorava.facebook.model.Tag;

import java.io.IOException;
import java.util.List;

class TagListDeserializer extends JsonDeserializer<List<Tag>> {
    @SuppressWarnings("unchecked")
    @Override
    public List<Tag> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = BeanResolver.getInstance().resolve(ObjectMapper.class);
        jp.setCodec(mapper);
        if (jp.hasCurrentToken()) {
            JsonNode dataNode = (JsonNode) jp.readValueAs(JsonNode.class).get("data");
            return (List<Tag>) mapper.reader(new TypeReference<List<Tag>>() {
            }).readValue(dataNode);
        }

        return null;
    }
}
