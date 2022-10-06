import { useState } from 'react';

function RecordForm({ record: initialRecord, notify }) {

    const [record, setRecord] = useState(initialRecord);
    const isAdd = initialRecord.id === 0;

    function handleChange(evt) {
        const clone = { ...record };
        clone[evt.target.name] = evt.target.value;
        setRecord(clone);
    }

    function handleSubmit(evt) {
        evt.preventDefault();

        const url = isAdd ? "http://localhost:8080/records" : `http://localhost:8080/records/${record.id}`;
        const method = isAdd ? "POST" : "PUT";
        const expectedStatus = isAdd ? 201 : 204;

        const init = {
            method,
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(record)
        };

        fetch(url, init)
            .then(response => {
                if (response.status === expectedStatus) {
                    if (isAdd) {
                        return response.json();
                    } else {
                        return record;
                    }
                }
                return Promise.reject(`Didn't receive expected status: ${expectedStatus}`);
            })
            .then(result => notify({
                action: isAdd ? "add" : "edit",
                record: result
            }))
            .catch(error => notify({ error: error }));

    }

    return (
        <>
            <h1>{record.id > 0 ? "Edit" : "Add"} Record</h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="artist">Artist</label>
                    <input type="text" id="artist" name="artist"
                        className="form-control"
                        value={record.artist} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="album">Album</label>
                    <input type="text" id="album" name="album"
                        className="form-control"
                        value={record.album} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <label htmlFor="year">Year</label>
                    <input type="text" id="year" name="year"
                        className="form-control"
                        value={record.year} onChange={handleChange} />
                </div>
                <div className="mb-3">
                    <button className="btn btn-primary mr-3" type="submit">Save</button>
                    <button className="btn btn-secondary" type="button" onClick={() => notify({ action: "cancel" })}>Cancel</button>
                </div>
            </form>
        </>
    );
}

export default RecordForm;