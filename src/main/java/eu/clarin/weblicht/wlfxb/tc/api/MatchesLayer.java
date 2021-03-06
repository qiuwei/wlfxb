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
package eu.clarin.weblicht.wlfxb.tc.api;

import java.util.List;
import java.util.Map;

/**
 * The <tt>MatchesLayer</tt> layer is present when the TCF data is generated by 
 * querying text corpus resources. The layer consists of the original query 
 * string and query type (ie the language of the query), and of corpus elements. 
 * Each corpus element has a name, a persistent identifier and a list of items. 
 * Each item references a token, or sequence of tokens, in the TCF tokens layer 
 * and a token, or sequence of tokens, in the original corpus. It specifies the 
 * target (if requested in the query), and categories (if additional information 
 * about the corresponding original resource can be given, such as document name, 
 * author, genre, etc.).
 * 
 * @author Yana Panchenko
 */
public interface MatchesLayer extends TextCorpusLayer {

    public String getQueryType();

    public String getQueryString();

    public MatchedCorpus getCorpus(int index);

    public MatchedCorpus addCorpus(String corpusName, String corpusPID);

    public MatchedItem addItem(MatchedCorpus corpusToAddItem,
            List<Token> itemTokens, List<String> itemOriginCorpusTokenIds);

    public MatchedItem addItem(MatchedCorpus corpusToAddItem,
            List<Token> itemTokens);

    public MatchedItem addItem(MatchedCorpus corpusToAddItem,
            List<Token> itemTokens, List<String> itemOriginCorpusTokenIds,
            Map<String, String> itemTargets, Map<String, String> itemCategories);

    public MatchedItem addItem(MatchedCorpus corpusToAddItem,
            List<Token> itemTokens,
            Map<String, String> itemTargets, Map<String, String> itemCategories);

    public Token[] getTokens(MatchedItem item);

    public MatchedItem getMatchedItem(Token token);
}
