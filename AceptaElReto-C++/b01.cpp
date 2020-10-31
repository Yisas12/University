#include<iostream>
#include<vector>
using namespace std;

/*P = {2 <= n <= longitud(v)}
Coste: el coste de este algoritmo es O(n) debido a que el vector se recorre una sola vez
de principio a fin
*/

int resolver(vector<int> v, const int cant) {
	int max = 0;
	int suma = 0;
	int i = 0;
	int j = 1;

	while (j < cant) {
		suma = v[i] + v[j];

		if (suma > max) max = suma;

		if (v[i] < v[j]) {
			i++;
		}
		j++;
	}

	return max;

	/*Por Marcos
	int max, submax;
	int i = 2;
	int ret;

	max = v[0];
	submax = v[1];

	if(max < submax) swap(max,submax);
	ret = max + submax;

	while(i < n){ //que ocurre con v[i]

		if(v[i] > submax){
			submax = v[i];

			if(max < submax) swap(max,submax);
			ret = max + submax;
		}

	i++;
	}
	

	return ret;

	*/
}

/*
I = {
(0 <= i <= n) ^ max = max a : 0 <= a < i : v[a] ^ 
submax = max b : 0 <= b < i ^ (b != a) : v[b] ^
ret = max + submax;
}
*/

void resuelveCaso() {
	int cant, nums;
	vector<int> vector;

	cin >> cant;

	if (cant == 0) cout << "0\n";
	else if (cant == 1) {
		cin >> nums;
		cout << nums << "\n";
	}
	else {
		for (int i = 0; i < cant; i++) {
			cin >> nums;
			vector.push_back(nums);
		}

		int p = resolver(vector, cant);

		cout << p << "\n";
	}
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}

	return 0;
}