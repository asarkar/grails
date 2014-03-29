/*
 * Copyright (c) 2014, the original author or authors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * A copy of the GNU General Public License accompanies this software,
 * and is also available at http://www.gnu.org/licenses.
 */

package name.abhijitsarkar.moviemanager.service.search

import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.index.IndexReader
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.store.Directory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import static org.springframework.util.Assert.notNull

/**
 * @author Abhijit Sarkar
 */
@Component
class MovieSearchServiceHelper {
    @Autowired
    private Directory indexDirectory

    private IndexReader indexReader
    private IndexSearcher indexSearcher

    @PostConstruct
    void postConstruct() {
        notNull(indexDirectory, 'Index directory must not be null.')

        indexReader = newIndexReader()
        indexSearcher = newIndexSearcher()
    }

    protected IndexReader newIndexReader() {
        DirectoryReader.open(indexDirectory)
    }

    protected IndexSearcher newIndexSearcher() {
        indexReader = newIndexReader()

        indexSearcher = new IndexSearcher(indexReader)
    }

    @PreDestroy
    void preDestroy() {
        indexReader?.close()
    }
}
