/* Copyright (c) 2016, 2017                                               */
/*       Institute of Software, Chinese Academy of Sciences               */
/* This file is part of ROLL, a Regular Omega Language Learning library.  */
/* ROLL is free software: you can redistribute it and/or modify           */
/* it under the terms of the GNU General Public License as published by   */
/* the Free Software Foundation, either version 3 of the License, or      */
/* (at your option) any later version.                                    */

/* This program is distributed in the hope that it will be useful,        */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          */
/* GNU General Public License for more details.                           */

/* You should have received a copy of the GNU General Public License      */
/* along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

package roll.learner;

import roll.main.Options;
import roll.query.Query;

/**
 * @M the output automata hypothesis such as DFA, NFA, FDFA or NBA 
 * @O output type like boolean values or other values
 * 
 * @author Yong Li (liyong@ios.ac.cn)
 * */
public interface Learner<M, O> {
	
	LearnerType getLearnerType();
	
	void startLearning();
	
	M getHypothesis();
	
	void refineHypothesis(Query<O> query);
	
	Options getOptions();
}
