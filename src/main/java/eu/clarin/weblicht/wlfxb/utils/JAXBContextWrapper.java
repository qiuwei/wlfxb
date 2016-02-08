/**
 * wlfxb - a library for creating and processing of TCF data streams.
 *
 * Copyright (C) University of Tübingen.
 *
 * This file is part of wlfxb.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.clarin.weblicht.wlfxb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.concurrent.locks.Lock;

/**
 * Created by wqiu on 2/8/16.
 */
public abstract class JAXBContextWrapper extends JAXBContext{
    public static final Logger LOGGER = LoggerFactory.getLogger(JAXBContextWrapper.class);
    public static int jaxbContextInitNum = 0;
    private static final Object LOCK = new Object[0];

    public static JAXBContext newInstance(Class clazz) throws JAXBException {
        synchronized (LOCK) {
            jaxbContextInitNum ++;
        }
        LOGGER.info("JAXBContext init {} times", jaxbContextInitNum);
        return JAXBContext.newInstance(clazz);
    }
}
