/**
 * Copyright 2012 Nuvola Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vividcode.imap.common.shared.vo;

import java.util.Date;

public class SpotVO {
    private Long id;
    private String title;
    private String description;
    private Date created;
    private String locationOwner;
    private LocationVO location;
    private UserVO user;
    private TypeSpotVO type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLocationOwner() {
        return locationOwner;
    }

    public void setLocationOwner(String locationOwner) {
        this.locationOwner = locationOwner;
    }

    public LocationVO getLocation() {
        return location;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public TypeSpotVO getType() {
        return type;
    }

    public void setType(TypeSpotVO type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Spot{id=" + id + ", title='" + title + '\'' + ", type=" + type + '}';
    }
}
