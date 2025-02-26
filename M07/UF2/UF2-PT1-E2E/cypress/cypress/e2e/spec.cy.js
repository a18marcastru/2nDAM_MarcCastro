describe('Pruebas E2E - UF2-PT1', () => {

  const baseUrl = 'http://cypress.dam.inspedralbes.cat/';
  
  it('Carga la página index y verifica contenido', () => {
    cy.visit(baseUrl);
    cy.contains('h1', 'Marc Castro');
    cy.contains('a', 'Projectes de GitHub').should('have.attr', 'href', 'projectes.html');
  });

  it('Navega a la página de proyectos y verifica los enlaces', () => {
    cy.visit(baseUrl); 
    cy.contains('a', 'Projectes de GitHub').click();

    cy.url().should('include', 'projectes.html');
    cy.contains('h1', 'Projectes');

    const projectLinks = [
      'tr0-dam-2024-25-a18marcastru',
      'tr0-dam-2024-25-app-android-a18marcastru',
      'transversal-tr1-2024-2025-front-back-dam_24_25_tr1g6',
      'tr1-NoOriginal-android'
    ];

    projectLinks.forEach((project) => {
      cy.contains('a', project).should('have.attr', 'href').and('include', 'github.com');
    });

    cy.contains('a', projectLinks[0]).click();
  });
});
