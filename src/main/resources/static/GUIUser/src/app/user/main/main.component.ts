import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {Tablename} from '../tablename';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html'
})
export class MainComponent implements OnInit {
  tableList: Tablename[] = [];
  iterator = 0;
  tableName = "";
  idd;
  constructor(private user:  UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.showTables();
    this.idd = this.route.snapshot.paramMap.get('t');

  }
   showTables() {
    this.user.getTables().subscribe(value => {
      console.log(value);
      this.tableList = value;
    });
  }
  getTableName(value1) {
    console.log(value1);
    this.user.getTablesPola(value1).subscribe(value => {
      console.log(value);
    });
  }

}
