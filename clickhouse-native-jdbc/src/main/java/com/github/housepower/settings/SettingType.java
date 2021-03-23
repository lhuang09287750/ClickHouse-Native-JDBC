/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.housepower.settings;

import com.github.housepower.io.CompositeSink;

import java.io.Serializable;
import java.time.Duration;

public interface SettingType<T extends Serializable> {

    Class<T> javaClass();

    T deserializeURL(String queryParameter);

    void serializeSetting(CompositeSink sink, T value);

    SettingType<Long> Int64 = new SettingType<Long>() {

        @Override
        public Class<Long> javaClass() {
            return Long.class;
        }

        @Override
        public Long deserializeURL(String queryParameter) {
            return Long.valueOf(queryParameter);
        }

        @Override
        public void serializeSetting(CompositeSink sink, Long value) {
            sink.writeVarInt(value);
        }
    };

    SettingType<Integer> Int32 = new SettingType<Integer>() {

        @Override
        public Class<Integer> javaClass() {
            return Integer.class;
        }

        @Override
        public Integer deserializeURL(String queryParameter) {
            return Integer.valueOf(queryParameter);
        }

        @Override
        public void serializeSetting(CompositeSink sink, Integer value) {
            sink.writeVarInt(value);
        }
    };

    SettingType<Float> Float32 = new SettingType<Float>() {

        @Override
        public Class<Float> javaClass() {
            return Float.class;
        }

        @Override
        public Float deserializeURL(String queryParameter) {
            return Float.valueOf(queryParameter);
        }

        @Override
        public void serializeSetting(CompositeSink sink, Float value) {
            sink.writeUTF8Binary(String.valueOf(value));
        }
    };

    SettingType<String> UTF8 = new SettingType<String>() {

        @Override
        public Class<String> javaClass() {
            return String.class;
        }

        @Override
        public String deserializeURL(String queryParameter) {
            return queryParameter;
        }

        @Override
        public void serializeSetting(CompositeSink sink, String value) {
            sink.writeUTF8Binary(String.valueOf(value));
        }
    };

    SettingType<Boolean> Bool = new SettingType<Boolean>() {

        @Override
        public Class<Boolean> javaClass() {
            return Boolean.class;
        }

        @Override
        public Boolean deserializeURL(String queryParameter) {
            return Boolean.valueOf(queryParameter);
        }

        @Override
        public void serializeSetting(CompositeSink sink, Boolean value) {
            sink.writeVarInt(Boolean.TRUE.equals(value) ? 1 : 0);
        }
    };

    SettingType<Duration> Seconds = new SettingType<Duration>() {

        @Override
        public Class<Duration> javaClass() {
            return Duration.class;
        }

        @Override
        public Duration deserializeURL(String queryParameter) {
            return Duration.ofSeconds(Long.parseLong(queryParameter));
        }

        @Override
        public void serializeSetting(CompositeSink sink, Duration value) {
            sink.writeVarInt(value.getSeconds());
        }
    };

    SettingType<Duration> Milliseconds = new SettingType<Duration>() {

        @Override
        public Class<Duration> javaClass() {
            return Duration.class;
        }

        @Override
        public Duration deserializeURL(String queryParameter) {
            return Duration.ofMillis(Long.parseLong(queryParameter));
        }

        @Override
        public void serializeSetting(CompositeSink sink, Duration value) {
            sink.writeVarInt(value.toMillis());
        }
    };

    SettingType<Character> Char = new SettingType<Character>() {

        @Override
        public Class<Character> javaClass() {
            return Character.class;
        }

        @Override
        public Character deserializeURL(String queryParameter) {
            return queryParameter.charAt(0);
        }

        @Override
        public void serializeSetting(CompositeSink sink, Character value) {
            sink.writeUTF8Binary(String.valueOf(value));
        }
    };
}