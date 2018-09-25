import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class RestService {

  constructor(private httpClient: HttpClient) {
  }

  get<R>(url: string): Observable<R> {
    return this.httpClient.get<R>(url);
  }

  getArray<R>(url: string): Observable<Array<R>> {
    return this.httpClient.get<Array<R>>(url);
  }

}
