/* HELPER FUNCIONS */
/* used to SEARCH the element X in the list and return true if it is found in list */
/* @args: element_to_be_searched
		  list_name
*/
is_contains(X,[X|_]).
is_contains(X,[_|T]):- is_contains(X,T).

/* test case path(nyc,sfo).	*/
edge(nyc,chicago).
edge(nyc,dallas).
edge(chicago, la).
edge(dallas, la).
edge(la, sfo).
edge(sfo,la).
edge(la, dallas).
edge(dallas, nyc).



path3(N,S, [N,S], Rec) :- \+is_contains(N, Rec),edge(N,S).
path3(N,S, [N|T], Rec) :- \+is_contains(N, Rec), edge(N, L), path3(L, S, T,[N | Rec]).

/* Main function */
path2(X, Y, [H|T]) :- path3(X,Y, [H|T], []).
