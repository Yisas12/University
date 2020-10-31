#include<iostream>
#include<vector>
using namespace std;

void cambiarLetras(vector<vector<char>> c, vector<vector<char>> ca, const int filas, const int columnas, const int daltonicos) {
	
	//primero lo cambiamos en el array cambio, es decir,
	//para el segundo ejemplo ponemos que la a sea directamente la y

	if (filas > 0 && columnas > 0) {
		char letra = ca[0][1];

		for (int i = 1; i < daltonicos; i++) {
			for (int j = i; j < daltonicos; j++) {
				if (letra == ca[j][0]) {
					ca[i - 1][1] = ca[j][1];
				}
			}
			letra = ca[i][1];
		}

		//ahora sustituimos en la matriz original
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				for (int k = 0; k < daltonicos; k++) { //recorre la matriz de cambio
					if (c[i][j] == ca[k][0]) {
						c[i][j] = ca[k][1];
					}
				}
				//imprimimos
				cout << c[i][j];
			}
			cout << "\n";
		}
	}
}

bool resuelveCaso() {
	int filas, columnas;
	int daltonicos;
	vector<vector<char>> c;
	vector<vector<char>> ca;
	//char cuadro[500][500] = { 'a' };
	//char cambiar[100000][2] = { 'a' };

	cin >> filas >> columnas;

	if (filas == 0 || columnas == 0) return false;

	for (int i = 0; i < filas; i++) {
		for (int j = 0; j < columnas; j++) {
			cin >> c[i][j];
		}
	}

	cin >> daltonicos;

	//en vez de guardarlo, cada vez que leemos uno vemos si se puede sustituir en la matriz
	for (int i = 0; i < daltonicos; i++) {
		for (int j = 0; j < 2; j++) {
			cin >> ca[i][j];
		}
	}

	cambiarLetras(c, ca, filas, columnas, daltonicos);

	return true;
}

int main() {
	while(resuelveCaso());
	return 0;
}