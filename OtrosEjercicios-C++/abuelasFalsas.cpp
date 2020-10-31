#include <iostream>
#include <string>
using namespace std;

/*void nombres()
{
	string v[1000];
	int num;
	string nombre1, nombre2;
	int i = 1;

	cin >> nombre1;
	cin >> num;
	cin >> nombre2;
	v[0] = nombre2;

	if (num == 1) cout << "FALSA" << endl;
	else
	{
		while (i < num && cin >> nombre2)
		{
			v[i] = nombre2;
			i++;
		}

		if (nombre1 == v[num - 1]) cout << "VERDADERA" << endl;
		else cout << "FALSA" << endl;
	}
}*/

void nombres()
{
	int cont = 0, num;
	string nombre1, nombre2;
	bool ok = true;

	cin >> nombre1;
	cin >> num;

	if (num == 1) {
		cout << "FALSA" << endl;
		cin.ignore(1000, '\n');
	}
	else
	{
		cin.ignore(1);

		for (int i = 1; i < num; i++)
		{
			cin >> nombre2;
			ok = ok && (nombre1 != nombre2);
		}

		cin >> nombre2; //ultimo nombre
		ok = ok && (nombre1 == nombre2);

		if (ok)
		{
			cout << "VERDADERA" << endl;
		}
		else
		{
			cout << "FALSA" << endl;
		}
	}
}

int main()
{
	int casos, i = 0;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		nombres();
	}

	//system("pause");
	return 0;
}