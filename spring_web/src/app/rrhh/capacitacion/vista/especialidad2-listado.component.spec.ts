import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Especialidad2ListadoComponent } from './especialidad2-listado.component';

describe('Especialidad2ListadoComponent', () => {
  let component: Especialidad2ListadoComponent;
  let fixture: ComponentFixture<Especialidad2ListadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Especialidad2ListadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Especialidad2ListadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
