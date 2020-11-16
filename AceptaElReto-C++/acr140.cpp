#include<iostream>
using namespace std;

int resolver(int num) {

	int suma = 0;
	//caso base
	if (num < 10) {
		cout << num;
		return num;
	}
	else { //caso recursivo
		
		suma = resolver(num / 10) + num%10;
		cout << " + " << num % 10;
	}

	return suma;
}

bool resuelveCaso() {
	int num;

	cin >> num;

	if (num < 0) return false;
	
	cout << " = " << resolver(num) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}