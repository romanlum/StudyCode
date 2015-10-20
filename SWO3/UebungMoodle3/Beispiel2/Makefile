CC = gcc
CFLAGS = -g -Og -ansi -pedantic -Wall -Wextra

LD = gcc
LDFLAGS = -lm

PROGRAM = graph

CFILES = $(wildcard *.c)
OFILES = $(CFILES:.c=.o)

%.o: %.c
	$(CC) $(CFLAGS) -c $<
	

graphm: $(OFILES)
	# Linker decides if libraries are used on ofiles 
	$(LD) -o $@ graph_algs.o dg_adt_m.o graph.o $(LDFLAGS)

graphl: $(OFILES)
        # Linker decides if libraries are used on ofiles
	$(LD) -o $@ graph_algs.o dg_adt_l.o graph.o $(LDFLAGS)

	
clean:
	rm -f $(OFILES) graphm graphl
	
depend:
	@cat < /dev/null > makedep
	@for i in $(CFILES); do \
		($(CC) -MM $$i >> makedep); done
	@echo "/^# BEGIN DEPENDENCIES\$$/+1,\$$d" > edcmds
	@echo "r makedep" >> edcmds
	@echo "wq" >> edcmds 	
	@ed -v - Makefile < edcmds
	@rm edcmds makedep
	@echo "# LINE REQUIRED FOR MAKEDEPEND" >> Makefile
	
# BEGIN DEPENDENCIES
dg_adt_l.o: dg_adt_l.c dg_adt.h
dg_adt_m.o: dg_adt_m.c dg_adt.h
graph_algs.o: graph_algs.c graph_algs.h dg_adt.h
graph.o: graph.c DG_ADT.h GRAPH_ALGS.h dg_adt.h
# LINE REQUIRED FOR MAKEDEPEND
