import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MateriaUpdateComponent } from './materia-update.component';

describe('MateriaUpdateComponent', () => {
  let component: MateriaUpdateComponent;
  let fixture: ComponentFixture<MateriaUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MateriaUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MateriaUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
