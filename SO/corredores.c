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