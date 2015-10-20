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
	$(LD) -o $@ dg_adt_m.o graph.o $(LDFLAGS)

graphl: $(OFILES)
        # Linker decides if libraries are used on ofiles
	$(LD) -o $@ dg_adt_l.o graph.o $(LDFLAGS)

	
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
DG_ADT_M.o: DG_ADT_M.c DG_ADT.h
graph.o: graph.c DG_ADT.h
# LINE REQUIRED FOR MAKEDEPEND
