#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <semaphore.h>

int bicis[2] = {0,0}; //izq, der respectivamente
int puente = 0; //0 izq 1 der
int esperar[2] = {0,0}; //izq y der
bool bidir = true;
pthread_mutex_t m;
pthread_condvar_t con_esp;

void accederPuente(int sentido){
	lock(m);
	
	if(!bidir || esperar[(sentido + 1) % 2] > 0){
		esperar[sentido]++;
	
	while(!bidir && (puente != sentido || esperar[(sentido + 1) % 2] > 0)){
		cond_wait(cond_Esp, m);
	}
	
	esperar[sentido]--;
	}
	
	bicis[sentido]++;
	
	if(bidir){
		if((bicis[sentido] > 2) && (bicis[(sentido + 1) % 2] == 0) && (esperar[(sentido + 1) % 2] == 0)){
			puente = sentido;
			bidir = false;
		}
	}
	
	unlock(m);
}

void salir_puente(){
	lock(m);
	
	bicis[sentido]--;
	
	if(!bidir){
		if(bicis[sentido]<= 2){
			bidir = true;
			cond_broadcast(cond_esp);
		}
	}
	unlock(m);
}

/*Ejercicio bocadillos con sem�foros
#define TIEMPO 2
#define N 10        //datos a producir
#define JAMON 0
#define TORTILLA 1

sem_t sem_estudiantes[2];
sem_t sem_cocinero;
sem_t mutex, id_sem;
int bocadillos[2] = {N,N};
int idGlobal = 0;

char * tipoBocadillo(int tipo){
	if(tipo == JAMON)
		return "jamon";
	else
		return "tortilla";
}

void * Productor(void * arg) {

  while (1) {
	  sem_wait(&sem_cocinero);
	  sem_wait(&mutex);

	  bocadillos[JAMON] = N;
	  bocadillos[TORTILLA] = N;

	  printf("Cocinero reponiendo\n");

	  sleep(TIEMPO);

	  sem_post(&sem_estudiantes[JAMON]);
	  sem_post(&sem_estudiantes[TORTILLA]);
	  sem_post(&mutex);
  }
  pthread_exit(0);
}


void * Consumidor(void* args) {
  int *tipo;
  tipo = (int*)args;
  int tipoBocata = *tipo;
  int id;

  sem_wait(&id_sem);
  id = idGlobal++;
  printf("Estudiante %d se dispone a tomar un bocadillo de %s\n", id, tipoBocadillo(tipo));
  sem_post(&id_sem);

  while (1) {
	  sem_wait(&mutex);
	  if(bocadillos[tipoBocata] == 0){
		  sem_post(&sem_cocinero);
		  printf("Estudiante %d espera a que repongan bocadillos de %s\n", id, tipoBocadillo(tipo));
		  sem_wait(&sem_estudiantes[tipoBocata]);
	  }
	  bocadillos[tipoBocata]--;
	  sem_post(&mutex);
	  printf("Estudiante %d comiendo bocadillo de %s \n", id, tipoBocadillo(tipo));
	  sleep(2);
  }

  pthread_exit(0);
}

int main(int argc, char *argv[]) {
  pthread_t pTh1;
  pthread_t cTh1, cTh2, cTh3;

  int i;
  int *jamon;
  int *tortilla;

  jamon = malloc(sizeof(int));
  *jamon = JAMON;
  tortilla = malloc(sizeof(int));
  *tortilla = TORTILLA;

  for(i = 0; i < 2; i++){
	  sem_init(&sem_estudiantes[i], 0, 1);
  }

  sem_init(&sem_cocinero, 0, 0);
  sem_init(&mutex, 0, 1);
  sem_init(&id_sem, 0, 1);

  pthread_create(&pTh1, NULL, Productor, NULL);

  pthread_create(&cTh1, NULL, Consumidor, (void*)jamon);
  pthread_create(&cTh2, NULL, Consumidor, (void*)jamon);
  pthread_create(&cTh3, NULL, Consumidor, (void*)tortilla);

  pthread_join(pTh1, NULL);
  pthread_join(cTh1, NULL);
  pthread_join(cTh2, NULL);
  pthread_join(cTh3, NULL);

  for(i = 0; i < 2; i++){
  	  sem_destroy(&sem_estudiantes[i]);
  }
  sem_destroy(&sem_cocinero);

  exit(0);
}



Ejercicio corredores array mutex 
//var globales
int salida = 0;
int puesto = 1;
mutex_t m;
mutex_t mutexes[4];
condvar_t pistola;
condvar_t relevo[4];
int testigo[4] = {0, 0, 0, 0};

void start_race(int id_corredor){
	int mi_equipo = id_corredor / 4;
	int mi_puesto = id_corredor % 4;
	
	lock(m);
	
	salida++;
	
	if(salida < 16) //si no están todos preparados
	{
		cond_wait(m, pistola);
	}
	else{
		cond_broadcast(pistola);
	}
	
	unlock(m);
	
	lock(mutexes[mi_equipo]);
	
	while(testigo[mi_equipo] != mi_puesto)
	{
		cond_wait(relevo[mi_equipo], mutexes[mi_equipo]);
	}
	
	unlock(mutexes[mi_equipo]);
}

void notify_arrival(int id_corredor)
{
	int mi_equipo = id_corredor / 4;
	int mi_puesto = id_corredor % 4;
	
	if(mi_puesto != 3) //si no soy el último
	{
		lock(mutexes[mi_equipo]);
		
		testigo[mi_equipo]++;
		cond_broadcast(relevo[mi_equipo]);
	}
	else{
		lock(m);
		printf("%d %d", mi_equipo, mi_puesto);
		puesto++;
		unlock(m);
	}
}
/*
