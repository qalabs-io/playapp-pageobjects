Exercice :

	Pour démarrer le projet Play lancer la commande : run.bat dans le repertoire C:\formations\selenium\Selenium 2\workspace\AppDemo
	
	Pour aller sur l'interface, entrer l'adresse suivante dans le navigateur : http://localhost:9000

	En utilisant les classe présente et le pattern PageObject, créez :
		- Completez les manques dans le test pour que celui-ci fonctionne correctement
		- Ajoutez un test permettant de supprimez tous les utilisateurs

Exercice :

	Pour démarrer le projet Play lancer la commande : run.bat dans le repertoire C:\formations\selenium\Selenium 2\workspace\AppDemo

	Pour aller sur l'interface, entrer l'adresse suivante dans le navigateur : http://localhost:9000

	En utilisant les classes présentes et en ajoutant de nouvelles classes suivant le pattern PageObject :
		- Completez le test 'testCreateDeleteUser' (dans UserTests.java) :
				- Pour cela, créez les "PageObject" manquants (AddUserPage, UserListPage, EditUserPage)
				Conseils : - EditUserPage devrait étendre AddUserPage
				           - Attention : l'action de sauvegarde peut aboutir à une page différente selon qu'elle
				           a donné lieu a une réussite ou un échec. Il faut donc écrire 2 méthodes pour l'action *
				           de sauvegarde
				- Dans le test, ajouter un cas où la sauvegarde d'un nouvel utilisateur produit une erreur
				(email ou mot de passe invalide), et détectez cette erreur.
		- Ajoutez maintenant un test qui :
			- ajouter plusieurs utilisateurs à la suite
			- supprime tous les utilisateurs.
