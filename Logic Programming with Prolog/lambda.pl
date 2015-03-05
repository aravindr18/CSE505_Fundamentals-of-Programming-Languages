/* Occurs free in function*/
occurs_free_in(X, v(X)).

occurs_free_in(X, l(Y,T)) :-
		X \== Y,
		occurs_free_in(X, T).
	 
occurs_free_in(X, a(T1,T2)) :-
		occurs_free_in(X, T1)  ;
		occurs_free_in(X, T2).

/* eta function */
eta(l(X,a(T,v(X)))):- \+occurs_free_in(X, T).
/* checks whether the term is abstaction */
check_for_abs(l(_,_)).

/* beta function */
beta(a(T1,T2)):- check_for_abs(T1).

/* beta for normal function for recursive checking the beta*/
beta2(a(l(X,T),_)).	/* simple case of application with abstraction */
beta2(a(T1,T2)):-beta2(T1);beta2(T2).	/* case of application */
beta2(l(X,T)):-beta2(T).	

/*eta for recursively checking in normal function */
eta2(l(X,a(T,v(X)))):- \+occurs_free_in(X, T);eta2(T).
eta2(a(T1,T2)):- eta2(T1);eta2(T2).

/* helper function of normal */
normal_help(T):- \+beta2(T), \+eta2(T).	/*recurse into it*/
/* a term is normal term if it does not contain any beta and eta redex */
normal(T):- normal_help(T).
