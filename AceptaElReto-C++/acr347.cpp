#include<iostream>
using namespace std;

int resolver(int ancho, int alto) {
	
	/*if (alto >= 10 && ancho >= 10) {
		if (ancho % alto == 0) return ancho / alto + suma;
		else if (alto % ancho == 0) return alto / ancho + suma;
		else {
			if (ancho > alto) {
				return resolver(ancho - alto, alto, suma + 1);
			}
			else if (alto > ancho) return resolver(ancho, alto - ancho, suma + 1);
			else return 1;
		}
	}*/

	if (alto < 10 || ancho < 10) return 0;
	else {
		if (ancho >= alto) return ancho / alto + resolver(ancho % alto, alto);
		else return alto / ancho + resolver(ancho, alto%ancho);
	}
}

bool resuelveCaso() {
	int ancho, alto;
	cin >> ancho >> alto;

	if (ancho == 0 && alto == 0) return false;

	cout << resolver(ancho, alto) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}