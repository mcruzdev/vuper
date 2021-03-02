class UploadErrorView {

	constructor(errors = []) {
		this.errors = errors;
	}

	_lines() {
		return this.errors.map(err => {
			return `
				<tr>
					<td>${err}</td>
				</tr>
			`;
		}).join(' ');
	}	
	
	view() {
	
		return `
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">Erros</th>
	    </tr>
	  </thead>
	  <tbody>
	    ${this._lines()}
	  </tbody>
	</table>`;
	
	}
}
